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
    @StateObject private var viewModel = ProjectListViewModel()
    @StateObject private var stateManager = ProjectListStateManager()

    //    MARK: Body
    var body: some View {
        ViewBody
            .task {
                await viewModel.updateDataSource(0)
            }
    }

    private var ViewBody: some View {
        MainFrameView(viewModel: viewModel, alertManager: stateManager) {
            ForEach(viewModel.projectList.reversed()) { project in
                NavigationLink(destination: TaskListView(project)) {
                    ProjectCardView(model: project, viewModel: viewModel)
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
    }
}
