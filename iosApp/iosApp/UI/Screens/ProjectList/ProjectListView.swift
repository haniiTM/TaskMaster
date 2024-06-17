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
    @EnvironmentObject var userRoleManager: UserRoleManager
    @EnvironmentObject var authManager: AuthManager

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
        .alert(isPresented: $stateManager.isNotificationPresented) {
            switch stateManager.activeAlert {
            case .logOut:
                DestructiveAlertTemplate("Выход из аккаунта") {
                    authManager.isAuthenticated = false
                } secondaryButtonAction: {
                    stateManager.isNotificationPresented = false
                }.body
            case .notification:
                NotificationAlert(viewModel.notificationItemList)
                    .body
            }
        }
    }

    private func updateDataSource() async {
        await viewModel.updateDataSource(0)
        await viewModel.updateNotificationItemList()

        if !viewModel.notificationItemList.isEmpty &&
            !userRoleManager.isAdmin
        {
            stateManager.activeAlert = .notification
            stateManager.isNotificationPresented = true
        }
    }
}
