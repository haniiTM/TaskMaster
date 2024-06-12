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
    private let attachment: FileDTO
    private let taskId: UInt16

    init(_ attachment: FileDTO,
         taskId: UInt16,
         viewModel: AttachmentListViewModel) {
        self.viewModel = viewModel
        self.taskId = taskId
        self.attachment = attachment
    }

    func download() async {
        await viewModel.downloadAttachment(attachment,
                                           taskId: taskId)
    }

    func delete() async {
        await viewModel.deleteAttachment(attachment,
                                         taskId: taskId)
    }
}
