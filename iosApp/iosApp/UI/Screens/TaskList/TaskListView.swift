//
//  TaskListView.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskListView: View {
    @StateObject private var viewModel = TaskListViewModel()
    @State private var taskList: [TaskInfo] = []
    private let title: String

    @State private var model = TaskListModel()

    init(_ title: String) {
        self.title = title
        //        viewModel.projectListSignal.bind { projectList in
        //            guard let projectList = projectList else { return }
        //            self.projectList = projectList
        //        }
    }

    var body: some View {
        ProjectFrameView(title) {
            EstimatesScreenInfoButton()

            TaskSectionBG {
                ForEach(model.taskList) { task in
                    TaskCardView(taskInfo: task)
                }

                TaskCreationButton()
            }

            CompletedTaskSectionBG {
                TaskCreationButton()
            }
        }.onAppear { viewModel.updateDataSource() }
    }
}
