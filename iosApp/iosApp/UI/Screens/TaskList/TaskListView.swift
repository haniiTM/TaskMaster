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
    @StateObject private var stateManager = TaskListAlertManager()
    private let model: ProjectInfo

    //    MARK: Init
    init(_ model: ProjectInfo) {
        self.model = model
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .task { await viewModel.updateDataSource(model.id) }
    }

    private var ViewBody: some View {
        ProjectFrameView(model.title) {
            NavigationLink(destination: EstimationCalendarView(model)) {
                EstimatesScreenInfoButton()
            }
            .tint(.primary)

            TaskSectionBG(isEmpty: viewModel.unCompletedTaskListSignal.isEmpty) {
                ForEach(viewModel.unCompletedTaskListSignal) { task in
                    NavigationLink(destination: SubTaskListView(model.title, model: task)) {
                        TaskCardView(model.id, model: task, viewModel: viewModel)
                    }.tint(.primary)
                }

                TaskCreationButton(stateManager: stateManager)
            }

            CompletedTaskSectionBG(isEmpty: viewModel.completedTaskListSignal.isEmpty) {
                ForEach(viewModel.completedTaskListSignal) { task in
                    NavigationLink(destination: SubTaskListView(model.title, model: task)) {
                        TaskCardView(model.id, model: task, viewModel: viewModel)
                    }.tint(.primary)
                }
            }
        }.sheet(isPresented: $stateManager.addTaskState) {
            TaskCreationAlert(model.id, alertManager: stateManager, viewModel: viewModel)
        }
    }
}
