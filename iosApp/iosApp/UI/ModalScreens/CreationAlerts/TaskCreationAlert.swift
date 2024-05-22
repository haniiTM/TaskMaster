//
//  TaskCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 22-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskCreationAlert: View {
    @ObservedObject private var viewModel: TaskListViewModel
    @ObservedObject private var alertManager: TaskListAlertManager

    @State private var title = ""
    @State private var estimatedTime = ""
    @State private var categoryMenuTitle = "Выбор категории"
    @State private var categoryId: UInt8 = 0

    init(alertManager: TaskListAlertManager, viewModel: TaskListViewModel) {
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
        Group {
            TextField(text: $title) {
                Text("Название задачи")
                    .padding()
            }

            TextField(text: $estimatedTime) {
                Text("Временная оценка")
                    .padding()
            }

            Menu {
                Button("Backend") {
                    categoryId = 0
                    categoryMenuTitle = "Backend"
                }
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

    private func addTask(_ title: String, estimatedTime: String, categoryId: UInt8) async {
        //        await viewModel.createTask(title)
        alertManager.addTaskState.toggle()
    }
}
