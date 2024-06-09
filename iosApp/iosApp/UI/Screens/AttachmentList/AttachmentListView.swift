//
//  AttachmentListView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct AttachmentListView<T: TaskListStateManagerProtocol>: View {
    //    MARK: Props
    @StateObject private var viewModel = AttachmentListViewModel()
    @ObservedObject private var stateManager: T

    private let taskId: UInt16
    private let projectTitle: String

    //    MARK: Init
    init(_ projectTitle: String,
         taskId: UInt16,
         stateManager: T) {
        self.taskId = taskId
        self.projectTitle = projectTitle
        self.stateManager = stateManager
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(projectTitle,
                         stateManager: stateManager,
                         viewModel: viewModel) {
            ViewBody
        }
                         .task {
                             await viewModel.updateDataSource(taskId)
                         }
    }

    @ViewBuilder
    private var ViewBody: some View {
        ForEach(viewModel.attachmentListSignal, id: \.id) { attachment in
            AttachmentCardView(attachment.comment ?? "Вложение №\(attachment.id ?? 0)")
        }.padding(.horizontal, 40)

        AttachmentCreationButton()
    }
}
