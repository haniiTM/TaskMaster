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

    //    MARK: Body
    var body: some View {
        ViewBody
            .task {
                await viewModel.updateDataSource()
            }
    }

    private var ViewBody: some View {
        MainFrameView {
            ForEach(viewModel.projectList) { project in
                NavigationLink(destination: TaskListView(project)) {
                    ProjectCardView(model: project)
                }
                .tint(.primary)
            }
        }
    }
}
