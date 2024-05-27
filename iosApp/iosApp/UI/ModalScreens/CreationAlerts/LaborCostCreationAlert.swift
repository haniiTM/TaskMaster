//
//  LaborCostCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 27-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct LaborCostCreationAlert: View {
    //    @ObservedObject private var viewModel: SubTaskListViewModel
    @ObservedObject private var stateManager: TaskInfoStateManager

    private let taskId: UInt16
    @State private var date = Date()
    @State private var note = ""
    @State private var spentTime = ""
    @State private var activityMenuTitle = "Деятельность"
    @State private var activityId: UInt8 = 0

    init(_ taskId: UInt16, stateManager: TaskInfoStateManager/*, viewModel: SubTaskListViewModel*/) {
        self.taskId = taskId
        self.stateManager = stateManager
        //        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Добавить трудозатрату")
        { ViewBody } action: {
            //            Task { await addLaborCost(title, estimatedTime: estimatedTime, categoryId: categoryId) }
            addLaborCost()
        }
    }

    private var ViewBody: some View {
        SubTaskCreationForm
        //            .task { await viewModel.getActivityList() }
    }

    @ViewBuilder
    private var SubTaskCreationForm: some View {
        DatePicker("Дата", selection: $date, displayedComponents: .date)

        TextField(text: $note) {
            Text("Комментарий")
                .padding()
        }

        TextField(text: $spentTime) {
            Text("Затраченное время")
                .padding()
        }

        Menu {
            //            ForEach(viewModel.activityListSignal, id: \.id) { activity in
            //                Button(activity.name) {
            //                    categoryId = UInt8(activity.id)
            //                    categoryMenuTitle = activity.name
            //                }
            //            }
        } label: {
            HStack {
                Text(activityMenuTitle)

                Spacer()

                Image(systemName: "arrow.uturn.down.circle").scaleEffect(x: -1)
            }
        }.tint(.primary)
    }

    private func addLaborCost() {
        //        let taskDto = TaskDTO()
        //        taskDto.name = title
        //
        //        let scope = Int32(estimatedTime) ?? 0
        //        taskDto.scope = .init(int: scope)
        //
        //        let typeofactivityid = Int32(categoryId)
        //        taskDto.typeofactivityid = .init(int: typeofactivityid)

        //        await viewModel.createTask(parentId, taskDto: taskDto)
        stateManager.isCreationAlertShown.toggle()
    }

}
