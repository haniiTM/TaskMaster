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
    @State private var projectList: [TaskInfo] = []
    @State private var model = ProjectListModel()

    //    MARK: Init
    init() {
//        viewModel.projectListSignal.bind { projectList in
//            guard let projectList = projectList else { return }
//            self.projectList = projectList
//        }
    }

    //    MARK: Body
    var body: some View {
        MainFrameView {
            ForEach(viewModel.projectListSignal.value ?? .init()) { project in
                NavigationLink(destination: TaskListView(.init())) {
                    ProjectCardView(model: project)
                }
                .foregroundColor(.primary)
                .background(
                    Color(uiColor: .secondarySystemBackground),
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )
            }
        }.onAppear { viewModel.updateDataSource() }
            .navigationTitle("Все проекты")
            .toolbar {
                Button(action: {}) {
                    Image(systemName: Constants.Strings.ImageNames.extraActionsImageName)
                        .foregroundColor(.primary)
                }
            }
    }
}
