//
//  AttachmentListView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct AttachmentListView: View {
    //    MARK: Props
    @StateObject private var viewModel = AttachmentListViewModel()
    private let taskId: UInt8
    private let projectTitle: String

    //    MARK: Init
    init(_ projectTitle: String, taskId: UInt8) {
        self.taskId = taskId
        self.projectTitle = projectTitle
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(projectTitle) {
            ViewBody
        }
        .navigationTitle(projectTitle)
        .task {
            await viewModel.updateDataSource(taskId)
        }
    }

    @ViewBuilder
    private var ViewBody: some View {
        ForEach(viewModel.attachmentListSignal, id: \.id) { attachment in
            AttachmentCardView(attachment.comment ?? "Вложение №\(attachment.id ?? 0)").foregroundColor(.primary)
        }.padding(.horizontal, 40)

        AttachmentCreationButton().foregroundColor(.primary)
    }
}
