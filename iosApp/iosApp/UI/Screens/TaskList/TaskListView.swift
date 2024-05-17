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
            }.foregroundColor(.primary)

            TaskSectionBG {
                ForEach(viewModel.unCompletedTaskListSignal) { task in
                    NavigationLink(destination: SubTaskListView(model.title, model: task)) {
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
                ForEach(viewModel.completedTaskListSignal) { task in
                    NavigationLink(destination: SubTaskListView(model.title, model: task)) {
                        TaskCardView(model: task)
                    }.foregroundColor(.primary)
                }
            }
        }
        .task {
            await viewModel.updateDataSource(model.id)
        }
        .navigationTitle(model.title)
        .toolbar {
            Button(action: {}) {
                Image(systemName: Constants.Strings.ImageNames.searchActionImageName)
                    .foregroundColor(.primary)
            }
        }
    }
}
