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
    private let model: TaskInfo

    //    MARK: Init
    init(_ title: String, model: TaskInfo) {
        self.title = title
        self.model = model
        descriptionState = model.description
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .task { await viewModel.updateDataSource(model.id) }
    }

    private var ViewBody: some View {
        ProjectFrameView(title,
                         stateManager: stateManager,
                         viewModel: viewModel) {
            NavigationLink(destination: TaskInfoView(title, taskId: model.id)) {
                ScreenInfoButton(model.title, isUrgent: false)
            }.tint(.primary)

            DescriptionBody

            NavigationLink(destination: AttachmentListView(title, 
                                                           taskId: model.id,
                                                          stateManager: stateManager)) {
                AttachmentsScreenInfoButton()
            }.tint(.primary)

            SubTaskSectionBG(isEmpty: viewModel.unCompletedSubTaskListSignal.isEmpty) {
                ForEach(viewModel.unCompletedSubTaskListSignal) { subTask in
                    SubTaskCardView(model.id, model: subTask, viewModel: viewModel)
                }

                SubTaskCreationButton(stateManager: stateManager)
            }

            CompletedTaskSectionBG(isEmpty: viewModel.completedSubTaskListSignal.isEmpty) {
                ForEach(viewModel.completedSubTaskListSignal) { subTask in
                    SubTaskCardView(model.id, model: subTask, viewModel: viewModel)
                }
            }
        }.sheet(isPresented: $stateManager.isCreationAlertShown) {
            SubTaskCreationAlert(model.id, stateManager: stateManager, viewModel: viewModel)
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
}
