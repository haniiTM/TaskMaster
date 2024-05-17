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
        MainFrameView {
            ForEach(viewModel.projectList) { project in
                NavigationLink(destination: TaskListView(project)) {
                    ProjectCardView(model: project)
                }
                .foregroundColor(.primary)
                .background(
                    Color(uiColor: .secondarySystemBackground),
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )
            }
        }
        .task {
            await viewModel.updateDataSource()
        }
        .navigationTitle("Все проекты")
        .toolbar {
            Button(action: {}) {
                Image(systemName: Constants.Strings.ImageNames.extraActionsImageName)
                    .foregroundColor(.primary)
            }
        }
    }
}
