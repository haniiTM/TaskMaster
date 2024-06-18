//
//  SubTaskListView.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct SubTaskListView: View {
    //    MARK: Props
    @StateObject private var viewModel = SubTaskListViewModel()
    @StateObject private var stateManager = SubTaskListStateManager()

    @FocusState private var isFocused
    @State private var descriptionState: String
    private let title: String
    private let projectId: UInt16
    private let model: TaskInfo

    @State private var searchText = ""
    private var filteredItems: (
        unCompletedTaskList: [TaskInfo],
        completedTaskList: [TaskInfo]
    ) {
        searchText.isEmpty
        ? (
            viewModel.unCompletedSubTaskListSignal
                .reversed(),

            viewModel.completedSubTaskListSignal
                .reversed()
        )

        : (
            viewModel.unCompletedSubTaskListSignal
                .filter {
                    $0.title.localizedCaseInsensitiveContains(searchText)
                },

            viewModel.completedSubTaskListSignal
                .filter {
                    $0.title.localizedCaseInsensitiveContains(searchText)
                }
        )
    }

    //    MARK: Init
    init(_ title: String, projectId: UInt16, model: TaskInfo) {
        self.title = title
        self.projectId = projectId
        self.model = model
        descriptionState = model.description
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .task { await updateDataSource() }
            .refreshable {
                Task { await updateDataSource() }
            }
            .sheet(isPresented: $stateManager.isUserListVisible) {
                UserListAlert(projectId, stateManager: stateManager, viewModel: viewModel)
            }
            .sheet(isPresented: $stateManager.isUserAdditionAlertVisible) {
                UserListAdditionAlert(projectId,
                                      stateManager: stateManager,
                                      viewModel: viewModel)
            }
            .sheet(isPresented: $stateManager.isCreationAlertShown) {
                SubTaskCreationAlert(model.id, stateManager: stateManager, viewModel: viewModel)
            }
            .alert(isPresented: $stateManager.isPendingTaskAlertPresented) {
                .init(title: Text("Ошибка"),
                      message: Text("Задаче с статусом \'В ожидании\' не может быть присвоен статус \'Готово\'"))
            }
    }

    private var ViewBody: some View {
        ProjectFrameView(title,
                         stateManager,
                         $searchText)
        {
            NavigationLink(destination: TaskInfoView(title,
                                                     projectId,
                                                     model.id)) {
                ScreenInfoButton(model.title, isUrgent: false)
            }.tint(.primary)

            DescriptionBody

            NavigationLink(destination: AttachmentListView(title,
                                                           model.id)) {
                AttachmentsScreenInfoButton()
            }.tint(.primary)

            SubTaskSectionBG(isEmpty: filteredItems.unCompletedTaskList.isEmpty) {
                ForEach(filteredItems.unCompletedTaskList) { subTask in
                    NavigationLink(destination: SubTaskListView(title,
                                                                projectId: projectId,
                                                                model: subTask)) {
                        SubTaskCardView(model.id,
                                        subTask,
                                        $stateManager.isPendingTaskAlertPresented,
                                        stateManager,
                                        viewModel)
                    }.tint(.primary)
                }

                SubTaskCreationButton(stateManager: stateManager)
            }

            CompletedTaskSectionBG(isEmpty: filteredItems.completedTaskList.isEmpty) {
                ForEach(filteredItems.completedTaskList) { subTask in
                    NavigationLink(destination: SubTaskListView(title,
                                                                projectId: model.id,
                                                                model: subTask)) {
                        SubTaskCardView(model.id,
                                        subTask,
                                        $stateManager.isPendingTaskAlertPresented,
                                        stateManager,
                                        viewModel)
                    }.tint(.primary)
                }
            }
        }
    }

    private var DescriptionBody: some View {
        VStack(spacing: 8) {
            TextEditor(text: $descriptionState)
                .frame(minHeight: 150)
                .border(.ultraThickMaterial, width: 2)
                .focused($isFocused)

            Button {
                Task {
                    descriptionState = descriptionState.isEmpty
                    ? "Описание отсутствует"
                    : descriptionState

                    isFocused = false

                    await viewModel.updateTaskDesc(model.id,
                                                   descriptionState)
                }
            } label: {
                Text("Сохранить")
                    .frame(maxWidth: .infinity)
                    .padding(8)
            }
            .tint(.white)
            .background(
                .tint,
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )
        }
    }

    private func updateDataSource() async {
        await viewModel.updateDataSource(model.id)
    }
}
