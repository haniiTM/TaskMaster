//
//  SubTaskCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 23-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct SubTaskCreationAlert: View {
    @ObservedObject private var viewModel: SubTaskListViewModel
    @ObservedObject private var stateManager: SubTaskListStateManager

    private let parentId: UInt16
    @State private var title = ""
    @State private var estimatedTime = ""
    @State private var categoryMenuTitle = "Выбор категории"
    @State private var categoryId: UInt8 = 0

    init(_ parentId: UInt16, stateManager: SubTaskListStateManager, viewModel: SubTaskListViewModel) {
        self.parentId = parentId
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Создать подзадачу")
        { ViewBody } action: {
            Task { await addSubTask(title, estimatedTime: estimatedTime, categoryId: categoryId) }
        }
    }

    private var ViewBody: some View {
        SubTaskCreationForm
//            .task { await viewModel.getCategoryList() }
    }

    private var SubTaskCreationForm: some View {
        Group {
            TextField(text: $title) {
                Text("Название подзадачи")
                    .padding()
            }

            TextField(text: $estimatedTime) {
                Text("Временная оценка")
                    .padding()
            }

            Menu {
//                ForEach(viewModel.categoryListSignal, id: \.id) { category in
//                    Button(category.name) {
//                        categoryId = UInt8(category.id)
//                        categoryMenuTitle = category.name
//                    }
//                }
            } label: {
                HStack {
                    Text(categoryMenuTitle)

                    Spacer()

                    Image(systemName: "arrowshape.right.circle")
                }
            }.tint(.primary)
        }
        .padding()
        .background(
            .secondary,
            in: RoundedRectangle(cornerRadius: 8, style: .continuous).stroke()
        )
    }

    private func addSubTask(_ title: String, estimatedTime: String, categoryId: UInt8) async {
        let taskDto = TaskDTO()
        taskDto.name = title

        let scope = Int32(estimatedTime) ?? 0
        taskDto.scope = .init(int: scope)

        let typeofactivityid = Int32(categoryId)
        taskDto.typeofactivityid = .init(int: typeofactivityid)

//        await viewModel.createTask(parentId, taskDto: taskDto)
        stateManager.isCreationAlertShown.toggle()
    }
}
