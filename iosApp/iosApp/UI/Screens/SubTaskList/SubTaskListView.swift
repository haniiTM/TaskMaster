//
//  SubTaskListView.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct SubTaskListView: View {
    @StateObject private var viewModel = SubTaskListViewModel()
    @State private var subTaskList: [TaskInfo] = []
    private let title: String

    @State private var model = SubTaskListModel()

    init(_ title: String) {
        self.title = title
        //        viewModel.projectListSignal.bind { projectList in
        //            guard let projectList = projectList else { return }
        //            self.projectList = projectList
        //        }
    }

    var body: some View {
        ProjectFrameView(title) {
            ScreenInfoButton(title, isUrgent: false)

            DescriptionBody

            AttachmentsScreenInfoButton()

            SubTaskSectionBG {
                ForEach(model.subTaskList) { subTask in
                    SubTaskCardView(model: subTask)
                }

                SubTaskCreationButton()
            }

            CompletedTaskSectionBG {
                SubTaskCreationButton()
            }
        }.onAppear { viewModel.updateDataSource() }
    }

    private var DescriptionBody: some View {
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure.")
            .padding()
            .border(Color.primary)
    }
}
