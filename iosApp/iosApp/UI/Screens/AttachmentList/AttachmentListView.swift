//
//  AttachmentListView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct AttachmentListView: View {
    //    MARK: Props
    @StateObject private var viewModel = AttachmentListViewModel()
    @StateObject private var stateManager = AttachmentListStateManager()

    private let taskId: UInt16
    private let projectTitle: String

    @State private var searchText = ""
    private var filteredItems: [FileDTO] {
        searchText.isEmpty
        ? viewModel.attachmentList
        : viewModel.attachmentList
            .filter {
                $0.orig_filename?.localizedCaseInsensitiveContains(searchText)
                ?? false
            }
    }

    //    MARK: Init
    init(_ projectTitle: String,
         _ taskId: UInt16)
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
                         stateManager,
                         $searchText)
        { attachmentList }
    }

    @ViewBuilder
    private var attachmentList: some View {
        ForEach(filteredItems, id: \.id) { attachment in
            AttachmentCardView(attachment,
                               taskId,
                               stateManager,
                               viewModel)
        }.padding(.horizontal, 40)

        AttachmentCreationButton(stateManager)
    }
}
