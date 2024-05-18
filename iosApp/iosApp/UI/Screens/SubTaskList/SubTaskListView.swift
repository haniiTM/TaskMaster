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
    private let title: String
    private let model: TaskInfo

    //    MARK: Init
    init(_ title: String, model: TaskInfo) {
        self.title = title
        self.model = model
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(title) {
            NavigationLink(destination: TaskInfoView(title, taskId: model.id)) {
                ScreenInfoButton(model.title, isUrgent: false)
            }.foregroundColor(.primary)

            DescriptionBody

            NavigationLink(destination: AttachmentListView(title, taskId: model.id)) {
                AttachmentsScreenInfoButton()
            }.foregroundColor(.primary)

            SubTaskSectionBG {
                ForEach(viewModel.unCompletedSubTaskListSignal) { subTask in
                    SubTaskCardView(model: subTask)
                }

                SubTaskCreationButton().foregroundColor(.primary)
            }

            CompletedTaskSectionBG {
                ForEach(viewModel.completedSubTaskListSignal) { subTask in
                    SubTaskCardView(model: subTask)
                }
            }
        }
        .task { await viewModel.updateDataSource(model.id) }
        .navigationTitle(title)
        .toolbar {
            Button(action: {}) {
                Image(systemName: Constants.Strings.ImageNames.searchActionImageName)
            }
        }

    }

    private var DescriptionBody: some View {
        Text(model.description)
            .padding()
            .background(
                Color(uiColor: .secondarySystemBackground),
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )
    }
}
