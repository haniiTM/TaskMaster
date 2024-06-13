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
    @StateObject private var stateManager = TaskListStateManager()
    private let model: ProjectInfo

    @State private var isSearching = false
    @State private var searchText = ""
    private var filteredItems: (
        unCompletedTaskList: [TaskInfo],
        completedTaskList: [TaskInfo]
    ) {
        searchText.isEmpty
        ? (
            viewModel.unCompletedTaskListSignal
                .reversed(),

            viewModel.completedTaskListSignal
                .reversed()
        )

        : (
            viewModel.unCompletedTaskListSignal
                .filter {
                    $0.title.localizedCaseInsensitiveContains(searchText)
                },

            viewModel.completedTaskListSignal
                .filter {
                    $0.title.localizedCaseInsensitiveContains(searchText)
                }
        )
    }

    //    MARK: Init
    init(_ model: ProjectInfo) {
        self.model = model
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .task { await viewModel.updateDataSource(model.id) }
            .sheet(isPresented: $stateManager.addTaskState) {
                TaskCreationAlert(model.id, alertManager: stateManager, viewModel: viewModel)
            }
            .sheet(isPresented: $stateManager.isUserListVisible) {
                UserListAlert(model.id,
                              stateManager: stateManager,
                              viewModel: viewModel)
            }
            .sheet(isPresented: $stateManager.isUserAdditionAlertVisible) {
                UserListAdditionAlert(model.id,
                                      stateManager: stateManager,
                                      viewModel: viewModel)
            }
            .searchable(text: $searchText,
                        placement: .navigationBarDrawer(displayMode: .always))
    }

    private var ViewBody: some View {
        ProjectFrameView(model.title,
                         stateManager: stateManager,
                         viewModel: viewModel) {
            NavigationLink(destination: EstimationTableView(model)) {
                EstimatesScreenInfoButton()
            }
            .tint(.primary)

            TaskSectionBG(isEmpty: viewModel.unCompletedTaskListSignal.isEmpty) {
                ForEach(filteredItems.unCompletedTaskList) { task in
                    NavigationLink(destination: SubTaskListView(model.title, projectId: model.id, model: task)) {
                        TaskCardView(model.id, model: task, viewModel: viewModel)
                    }.tint(.primary)
                }

                TaskCreationButton(stateManager: stateManager)
            }

            CompletedTaskSectionBG(isEmpty: viewModel.completedTaskListSignal.isEmpty) {
                ForEach(filteredItems.completedTaskList) { task in
                    NavigationLink(destination: SubTaskListView(model.title, projectId: model.id, model: task)) {
                        TaskCardView(model.id, model: task, viewModel: viewModel)
                    }.tint(.primary)
                }
            }
        }
    }
}
