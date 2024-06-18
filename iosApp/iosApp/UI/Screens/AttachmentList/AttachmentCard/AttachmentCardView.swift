//
//  AttachmentCardView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct AttachmentCardView: View {
    //    MARK: Props
    private let controller: AttachmentCardController
    private let title: String
    private let imageName: String

    //    MARK: Init
    init(_ attachment: FileDTO,
         _ taskId: UInt16,
         _ stateManager: AttachmentListStateManager,
         _ viewModel: AttachmentListViewModel)
    {
        controller = AttachmentCardController(attachment,
                                              taskId,
                                              stateManager,
                                              viewModel)

        title = "\(attachment.orig_filename ?? .init()).\(attachment.type ?? .init())"
        imageName = Constants.Strings.ImageNames.extraActionsImageName
    }

    //    MARK: Body
    var body: some View {
        attachmentCard
            .alert(isPresented: controller.$isDeletionAlertPresented) {
                DestructiveAlertTemplate(controller.deletionAlertTitle)
                {
                    Task { await controller.delete() }
                } secondaryButtonAction: {
                    controller.hideDeletionAlert()
                }.body
            }
    }

    private var attachmentCard: some View {
        HStack {
            Text(title)
                .padding()

            Spacer()

            Menu {
                Button {
                    Task {
                        await controller.download()
                    }
                } label: {
                    Label("Скачать", systemImage: "square.and.arrow.down")
                }

                Button(role: .destructive) {
                    controller.showDeletionAlert()
                } label: {
                    Label("Удалить", systemImage: "delete.left")
                }
            } label: {
                Image(systemName: imageName)
                    .padding()
            }
        }
        .tint(.primary)
        .background(
            Color(uiColor: .secondarySystemBackground),
            in: RoundedRectangle(cornerRadius: 8, style: .continuous)
        )
    }
}
