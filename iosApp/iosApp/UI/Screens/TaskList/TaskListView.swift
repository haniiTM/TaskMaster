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
            .navigationBarBackButtonHidden(true)
            .task { await updateDataSource() }
            .refreshable {
                Task { await updateDataSource() }
            }
            .alert(isPresented: $stateManager.isPendingTaskAlertPresented) {
                .init(title: Text("Ошибка"),
                      message: Text("Задаче с статусом \'В ожидании\' не может быть присвоен статус \'Готово\'"))
            }
    }

    private var ViewBody: some View {
        ZStack {
            ProjectFrameView(model.title,
                             stateManager,
                             $searchText) {
                NavigationLink(destination: EstimationTableView(model)) {
                    EstimatesScreenInfoButton()
                }
                .tint(.primary)

                TaskSectionBG(isEmpty: filteredItems.unCompletedTaskList.isEmpty) {
                    ScrollView(showsIndicators: false) {
                        VStack(spacing: 16) {
                            ForEach(filteredItems.unCompletedTaskList) { task in
                                NavigationLink(destination: SubTaskListView(model.title, projectId: model.id, model: task)) {
                                    TaskCardView(model.id,
                                                 $stateManager.isPendingTaskAlertPresented,
                                                 task,
                                                 stateManager,
                                                 viewModel)
                                }.tint(.primary)
                            }
                        }
                        .padding()
                        .background(
                            filteredItems.unCompletedTaskList.isEmpty ? .clear : .shadowGray,
                            in: RoundedRectangle(cornerRadius: 25, style: .continuous)
                        )
                    }.frame(minHeight: 250, maxHeight: 350)


                    TaskCreationButton(stateManager: stateManager)
                }

                CompletedTaskSectionBG(isEmpty: filteredItems.completedTaskList.isEmpty) {
                    ScrollView(showsIndicators: false) {
                        VStack(spacing: 16) {
                            ForEach(filteredItems.completedTaskList) { task in
                                NavigationLink(destination: SubTaskListView(model.title,
                                                                            projectId: model.id,
                                                                            model: task)) {
                                    TaskCardView(model.id,
                                                 $stateManager.isPendingTaskAlertPresented,
                                                 task,
                                                 stateManager,
                                                 viewModel)
                                }.tint(.primary)
                            }
                        }
                        .padding()
                        .background(
                            filteredItems.unCompletedTaskList.isEmpty ? .clear : .shadowGray,
                            in: RoundedRectangle(cornerRadius: 25, style: .continuous)
                        )
                    }.frame(minHeight: 250, maxHeight: 350)
                }
            }

            if stateManager.addTaskState {
                AlertContainer($stateManager.addTaskState) {
                    TaskCreationAlert(model.id, alertManager: stateManager, viewModel: viewModel)
                }
            }

            if stateManager.isUserListVisible {
                AlertContainer($stateManager.isUserListVisible) {
                    UserListAlert(model.id,
                                  stateManager: stateManager,
                                  viewModel: viewModel)
                }
            }

            if stateManager.isUserAdditionAlertVisible {
                AlertContainer($stateManager.isUserAdditionAlertVisible) {
                    UserListAdditionAlert(model.id,
                                          stateManager: stateManager,
                                          viewModel: viewModel)
                }
            }
        }
    }

    private func updateDataSource() async {
        await viewModel.updateDataSource(model.id)
    }
}
