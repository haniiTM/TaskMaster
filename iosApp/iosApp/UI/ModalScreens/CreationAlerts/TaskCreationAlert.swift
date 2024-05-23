//
//  TaskCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 22-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct TaskCreationAlert: View {
    @ObservedObject private var viewModel: TaskListViewModel
    @ObservedObject private var alertManager: TaskListAlertManager

    private let parentId: UInt16
    @State private var title = ""
    @State private var estimatedTime = ""
    @State private var categoryMenuTitle = "Выбор категории"
    @State private var categoryId: UInt8 = 0

    init(_ parentId: UInt16, alertManager: TaskListAlertManager, viewModel: TaskListViewModel) {
        self.parentId = parentId
        self.alertManager = alertManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Создать задачу")
        { ViewBody } action: {
            Task { await addTask(title, estimatedTime: estimatedTime, categoryId: categoryId) }
        }
    }

    private var ViewBody: some View {
        TaskCreationForm
            .task { await viewModel.getCategoryList() }
    }

    @ViewBuilder
    private var TaskCreationForm: some View {
        TextField(text: $title) {
            Text("Название задачи")
                .padding()
        }

        TextField(text: $estimatedTime) {
            Text("Временная оценка")
                .padding()
        }

        Menu {
            ForEach(viewModel.categoryListSignal, id: \.id) { category in
                Button(category.name) {
                    categoryId = UInt8(category.id)
                    categoryMenuTitle = category.name
                }
            }
        } label: {
            HStack {
                Text(categoryMenuTitle)

                Spacer()

                Image(systemName: "arrow.uturn.down.circle").scaleEffect(x: -1)
            }
        }.tint(.primary)
    }

    private func addTask(_ title: String, estimatedTime: String, categoryId: UInt8) async {
        let taskDto = TaskDTO()
        taskDto.name = title

        let scope = Int32(estimatedTime) ?? 0
        taskDto.scope = .init(int: scope)

        let typeofactivityid = Int32(categoryId)
        taskDto.typeofactivityid = .init(int: typeofactivityid)

        await viewModel.createTask(parentId, taskDto: taskDto)
        alertManager.addTaskState.toggle()
    }
}
