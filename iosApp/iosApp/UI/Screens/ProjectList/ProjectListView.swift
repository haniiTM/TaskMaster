//
//  ProjectListView.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectListView: View {
    //    MARK: Props
    @EnvironmentObject var authViewModel: AuthViewModel

    @StateObject private var viewModel = ProjectListViewModel()
    @StateObject private var stateManager = ProjectListStateManager()

    @State private var searchText = ""
    private var filteredItems: [TaskInfo] {
        searchText.isEmpty

        ? viewModel.projectList
            .reversed()

        : viewModel.projectList
            .filter {
                $0.title.localizedCaseInsensitiveContains(searchText)
            }
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .task {
                await updateDataSource()
            }
            .refreshable {
                Task { await updateDataSource() }
            }
    }

    private var ViewBody: some View {
        MainFrameView($searchText,
                      stateManager)
        {
            ForEach(filteredItems) { project in
                NavigationLink(destination: TaskListView(project)) {
                    ProjectCardView(project, stateManager, viewModel) { EmptyView() }
                }
                .tint(.primary)
            }
        }
        .sheet(isPresented: $stateManager.addProjectState) {
            ProjectCreationAlert(alertManager: stateManager, viewModel: viewModel)
        }
        .sheet(isPresented: $stateManager.addUserState) {
            UserCreationAlert(stateManager, viewModel: viewModel)
        }
        .sheet(isPresented: $stateManager.deleteUserState, content: {
            UserListDeletionAlert(stateManager, viewModel: viewModel)
        })
        .alert(isPresented: $stateManager.isLogOutAlertPresented) {
            DestructiveAlertTemplate("Выход из аккаунта") {
                authViewModel.isAuthenticated = false
            } secondaryButtonAction: {
                stateManager.isLogOutAlertPresented = false
            }.body
        }
    }

    private func updateDataSource() async {
        await viewModel.updateDataSource(0)
    }
}
