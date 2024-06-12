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
    private let taskId: UInt16
    private let attachmentId: KotlinInt
    private let descriptionId: KotlinInt

    init(viewModel: AttachmentListViewModel,
         taskId: UInt16,
         attachmentId: KotlinInt,
         descriptionId: KotlinInt) {
        self.viewModel = viewModel
        self.taskId = taskId
        self.attachmentId = attachmentId
        self.descriptionId = descriptionId
    }

    func download() async {
        await viewModel.downloadAttachment(taskId: taskId,
                                           attachmentId: attachmentId)
    }

    func delete() async {
        await viewModel.deleteAttachment(taskId: taskId,
                                         attachmentId: attachmentId,
                                         descriptionId: descriptionId)
    }
}
