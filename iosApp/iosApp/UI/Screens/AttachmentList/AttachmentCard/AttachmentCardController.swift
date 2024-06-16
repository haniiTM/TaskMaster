//
//  AttachmentCardController.swift
//  TaskMaster
//
//  Created by  user on 11-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct AttachmentCardController {
    @ObservedObject private var viewModel: AttachmentListViewModel
    @ObservedObject private var stateManager: AttachmentListStateManager
    private let attachment: FileDTO
    private let taskId: UInt16

    let deletionAlertTitle = "Удаленить вложение"
    @Binding var isDeletionAlertPresented: Bool

    init(_ attachment: FileDTO,
         _ taskId: UInt16,
         _ stateManager: AttachmentListStateManager,
         _ viewModel: AttachmentListViewModel) {
        self.attachment = attachment
        self.taskId = taskId
        self.stateManager = stateManager
        self.viewModel = viewModel

        _isDeletionAlertPresented = .init(get: {
            stateManager.isDeletionAlertPresented
        }, set: { value in
            stateManager.isDeletionAlertPresented = value
        })
    }

    func download() async {
        await viewModel.downloadAttachment(attachment,
                                           taskId: taskId)
    }

    func delete() async {
        await viewModel.deleteAttachment(attachment,
                                         taskId: taskId)
    }

    func showDeletionAlert() {
        stateManager.isDeletionAlertPresented = true
    }

    func hideDeletionAlert() {
        stateManager.isDeletionAlertPresented = false
    }
}
