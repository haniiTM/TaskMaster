//
//  TaskListView.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskListView: View {
    //    MARK: Props
    @StateObject private var viewModel = TaskListViewModel()
    @State private var unCompletedTaskList: [TaskInfo] = []
    @State private var completedTaskList: [TaskInfo] = []
    private let title: String

    //    MARK: Init
    init(_ title: String) {
        self.title = title
        //        viewModel.projectListSignal.bind { projectList in
        //            guard let projectList = projectList else { return }
        //            self.projectList = projectList
        //        }

    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(title) {
            NavigationLink(destination: EstimationCalendarView("")) {
                EstimatesScreenInfoButton()
            }.foregroundColor(.primary)

            TaskSectionBG {
                ForEach(unCompletedTaskList) { task in
                    NavigationLink(destination: SubTaskListView("")) {
                        TaskCardView(model: task)
                    }.foregroundColor(.primary)
                }

                TaskCreationButton()
                    .foregroundColor(.primary)
                    .onTapGesture {
                        viewModel.addUncompletedTask()
                    }
            }

            CompletedTaskSectionBG {
                ForEach(completedTaskList) { task in
                    NavigationLink(destination: SubTaskListView("")) {
                        TaskCardView(model: task)
                    }.foregroundColor(.primary)
                }

                TaskCreationButton()
                    .foregroundColor(.primary)
                    .onTapGesture {
                        viewModel.addCompletedTask()
                    }
            }
        }
        .onAppear {
            viewModel.unCompletedTaskListSignal.bind { taskList in
                guard let taskList = taskList else { return }
                unCompletedTaskList = taskList
            }

            viewModel.completedTaskListSignal.bind { taskList in
                guard let taskList = taskList else { return }
                completedTaskList = taskList
            }

            viewModel.updateDataSource()
        }
        .navigationTitle("Сайт Nissan")
        .toolbar {
            Button(action: {}) {
                Image(systemName: Constants.Strings.ImageNames.searchActionImageName)
                    .foregroundColor(.primary)
            }
        }
    }
}
