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
    @State private var subTaskList: [TaskInfo] = []
    private let title: String

    @State private var model = SubTaskListModel()

    //    MARK: Init
    init(_ title: String) {
        self.title = title
        //        viewModel.projectListSignal.bind { projectList in
        //            guard let projectList = projectList else { return }
        //            self.projectList = projectList
        //        }
    }

    //    MARK: Body
    var body: some View {
        ProjectFrameView(title) {
            NavigationLink(destination: TaskInfoListView("", model: model.subTaskList.first!)) {
                ScreenInfoButton("Изучение React Native", isUrgent: false)
            }.foregroundColor(.primary)

            DescriptionBody

            NavigationLink(destination: AttachmentListView("")) {
                AttachmentsScreenInfoButton()
            }.foregroundColor(.primary)

            SubTaskSectionBG {
                ForEach(model.subTaskList) { subTask in
                    SubTaskCardView(model: subTask)
                }

                SubTaskCreationButton().foregroundColor(.primary)
            }

            CompletedTaskSectionBG {
                SubTaskCreationButton().foregroundColor(.primary)
            }
        }.onAppear { viewModel.updateDataSource() }
            .navigationTitle("Сайт Nissan")
            .toolbar {
                Button(action: {}) {
                    Image(systemName: Constants.Strings.ImageNames.searchActionImageName)
                }
            }
        
    }

    private var DescriptionBody: some View {
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure.")
            .padding()
            .background(
                Color(uiColor: .secondarySystemBackground),
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )
    }
}
