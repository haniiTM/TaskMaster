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
    @StateObject private var stateManager = AttachmentListStateManager()

    private let taskId: UInt16
    private let projectTitle: String

    //    MARK: Init
    init(_ projectTitle: String,
         taskId: UInt16)
    {
        self.taskId = taskId
        self.projectTitle = projectTitle
    }

    //    MARK: Body
    var body: some View {
        viewBody
            .task {
                await viewModel.updateDataSource(taskId)
            }
            .fileImporter(
                isPresented: $stateManager.isFileImporterVisible,
                allowedContentTypes: [.item]
            ) { result in
                switch result {
                case .success(let url):
                    Task { await viewModel.addAttachment(url, taskId) }

                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
    }

    private var viewBody: some View {
        ProjectFrameView(projectTitle,
                         stateManager: stateManager,
                         viewModel: viewModel)
        { attachmentList }
    }

    @ViewBuilder
    private var attachmentList: some View {
        ForEach(viewModel.attachmentList, id: \.id) { attachment in
            AttachmentCardView(attachment,
                               taskId: taskId,
                               viewModel: viewModel)
        }.padding(.horizontal, 40)

        AttachmentCreationButton(stateManager)
    }
}
