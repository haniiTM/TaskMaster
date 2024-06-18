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
    @ObservedObject private var alertManager: TaskListStateManager

    private let parentId: UInt16
    @State private var isEmpty = true
    @State private var title = ""
    @State private var estimatedTime = ""
    @State private var categoryMenuTitle = "Выбор категории"
    @State private var categoryId: UInt8 = 0

    init(_ parentId: UInt16, alertManager: TaskListStateManager, viewModel: TaskListViewModel) {
        self.parentId = parentId
        self.alertManager = alertManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Создать задачу", $isEmpty)
        { ViewBody } action: {
            Task { await addTask() }
        }
    }

    private var ViewBody: some View {
        TaskCreationForm
            .task { await viewModel.getCategoryList() }
            .onChange(of: title) { _ in checkIfEmpty() }
            .onChange(of: estimatedTime) { _ in checkIfEmpty() }
            .onChange(of: categoryMenuTitle) { _ in checkIfEmpty() }
    }

    @ViewBuilder
    private var TaskCreationForm: some View {
        CustomTextField("Название задачи",
                        $title)

        CustomTextField("Временная оценка",
                        $estimatedTime)

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

    private func addTask() async {
        let taskDto = TaskDTO()
        taskDto.name = title

        let scope = Int32(estimatedTime) ?? 0
        taskDto.scope = .init(int: scope)

        let typeofactivityid = Int32(categoryId)
        taskDto.typeofactivityid = .init(int: typeofactivityid)

        await viewModel.createTask(parentId, taskDto: taskDto)
        alertManager.addTaskState.toggle()
    }

    private func checkIfEmpty() {
        isEmpty = title.isEmpty || estimatedTime.isEmpty || categoryMenuTitle == "Выбор категории"
    }
}
