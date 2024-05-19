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
    private let model: ProjectInfo

    //    MARK: Init
    init(_ model: ProjectInfo) {
        self.model = model
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(model.title) {
            NavigationLink(destination: EstimationCalendarView(model)) {
                EstimatesScreenInfoButton()
            }
            .tint(.primary)

            TaskSectionBG(isEmpty: viewModel.unCompletedTaskListSignal.isEmpty) {
                ForEach(viewModel.unCompletedTaskListSignal) { task in
                    NavigationLink(destination: SubTaskListView(model.title, model: task)) {
                        TaskCardView(model: task)
                    }.tint(.primary)
                }

                TaskCreationButton()
                    .onTapGesture {
                        viewModel.addUncompletedTask()
                    }
            }

            CompletedTaskSectionBG(isEmpty: viewModel.completedTaskListSignal.isEmpty) {
                ForEach(viewModel.completedTaskListSignal) { task in
                    NavigationLink(destination: SubTaskListView(model.title, model: task)) {
                        TaskCardView(model: task)
                    }.tint(.primary)
                }
            }
        }
        .task {
            await viewModel.updateDataSource(model.id)
        }
    }
}
