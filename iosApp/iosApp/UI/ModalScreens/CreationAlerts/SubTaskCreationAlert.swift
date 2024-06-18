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
    @State private var isEmpty = true

    init(_ parentId: UInt16, stateManager: SubTaskListStateManager, viewModel: SubTaskListViewModel) {
        self.parentId = parentId
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Новая задача",
                              "Создать",
                              $isEmpty)
        { ViewBody } action: {
            Task { await addSubTask() }
        }
    }

    private var ViewBody: some View {
        SubTaskCreationForm
            .task { await viewModel.getCategoryList() }
            .onChange(of: title) { _ in checkIfEmpty() }
            .onChange(of: estimatedTime) { _ in checkIfEmpty() }
            .onChange(of: categoryMenuTitle) { _ in checkIfEmpty() }
    }

    @ViewBuilder
    private var SubTaskCreationForm: some View {
        CustomTextField("Название задачи",
                        $title)

        CustomTextField("Оценка времени",
                        $estimatedTime)
        .onChange(of: estimatedTime) { value in
            estimatedTime = value.formatInput()
        }

        Menu {
            ForEach(viewModel.categoryListSignal.reversed(), id: \.id) { category in
                Button {
                    categoryId = UInt8(category.id)
                    categoryMenuTitle = category.name
                } label: {
                    Label {
                        Text(category.name)
                    } icon: {
                        Image(systemName: category.name.getIconByRole())
                    }
                }
            }
        } label: {
            HStack {
                Text(categoryMenuTitle)

                Spacer()

                if categoryMenuTitle == "Выбор категории" {
                    Image(systemName: "exclamationmark.triangle")
                        .tint(.pink)
                }

                Image(systemName: "arrow.uturn.down.circle").scaleEffect(x: -1)
            }
        }.tint(.primary)
    }

    private func addSubTask() async {
        let taskDto = TaskDTO()
        taskDto.name = title

        let formattedEstimatedTime = estimatedTime.replacingOccurrences(of: ":", with: "")
        let scope = Int32(formattedEstimatedTime) ?? 0
        taskDto.scope = .init(int: scope)

        let typeofactivityid = Int32(categoryId)
        taskDto.typeofactivityid = .init(int: typeofactivityid)

        await viewModel.createTask(parentId, taskDto: taskDto)
        stateManager.isCreationAlertShown.toggle()
    }

    private func checkIfEmpty() {
        isEmpty =
        title.isEmpty ||
        estimatedTime.isEmpty ||
        estimatedTime.count < 5 ||
        categoryMenuTitle == "Выбор категории"
    }
}
