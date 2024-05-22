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
    @StateObject private var alertManager = ProjectListAlertManager()

    //    MARK: Body
    var body: some View {
        ViewBody
            .task {
                await viewModel.updateDataSource()
            }
    }

    private var ViewBody: some View {
        MainFrameView(viewModel: viewModel, alertManager: alertManager) {
            ForEach(viewModel.projectList.reversed()) { project in
                NavigationLink(destination: TaskListView(project)) {
                    ProjectCardView(model: project, viewModel: viewModel)
                }
                .tint(.primary)
            }
        }.sheet(isPresented: $alertManager.addProjectState) {
            ProjectCreationAlert(alertManager: alertManager, viewModel: viewModel)
        }
    }
}
