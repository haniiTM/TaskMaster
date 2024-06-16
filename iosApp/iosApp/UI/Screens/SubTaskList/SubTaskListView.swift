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
    }

    private var ViewBody: some View {
        ProjectFrameView(title,
                         stateManager,
                         $searchText) {
            NavigationLink(destination: TaskInfoView(title,
                                                     projectId: projectId,
                                                     taskId: model.id)) {
                ScreenInfoButton(model.title, isUrgent: false)
            }.tint(.primary)

            DescriptionBody

            NavigationLink(destination: AttachmentListView(title,
                                                           model.id)) {
                AttachmentsScreenInfoButton()
            }.tint(.primary)

            SubTaskSectionBG(isEmpty: filteredItems.unCompletedTaskList.isEmpty) {
                ForEach(filteredItems.unCompletedTaskList) { subTask in
                    SubTaskCardView(model.id,
                                    subTask,
                                    stateManager,
                                    viewModel)
                }

                SubTaskCreationButton(stateManager: stateManager)
            }

            CompletedTaskSectionBG(isEmpty: filteredItems.completedTaskList.isEmpty) {
                ForEach(filteredItems.completedTaskList) { subTask in
                    SubTaskCardView(model.id,
                                    subTask,
                                    stateManager,
                                    viewModel)
                }
            }
        }
    }

    private var DescriptionBody: some View {
        //        TextEditor(text: $descriptionState)
        VStack(spacing: 8) {
            Text(model.description)
                .frame(maxWidth: .infinity, alignment: .topLeading)
                .padding()
                .padding(.bottom, 64)
                .background(
                    Color(uiColor: .secondarySystemBackground),
                    in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                )

            Button(action: {}) {
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
