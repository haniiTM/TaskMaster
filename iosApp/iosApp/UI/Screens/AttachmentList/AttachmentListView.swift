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
    private let viewModel: AttachmentListViewModelProtocol
    private let title: String
    private let attachmentList: [String]

    //    MARK: Init
    init(_ title: String) {
        self.title = title
        viewModel = AttachmentListViewModel()
        viewModel.updateDataSource()
        attachmentList = viewModel.attachmentListSignal.value ?? .init()
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(title) {
            ForEach(attachmentList, id: \.self) { attachment in
                AttachmentCardView(attachment).foregroundColor(.primary)
            }.padding(.horizontal, 40)

            AttachmentCreationButton().foregroundColor(.primary)
        }.navigationTitle("Вложения")
    }
}
