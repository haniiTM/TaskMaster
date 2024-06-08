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
    @ObservedObject private var viewModel: TaskInfoViewModel
    @ObservedObject private var stateManager: TaskInfoStateManager

    private let taskId: UInt16
    @State private var date = Date()
    @State private var note = ""
    @State private var spentTime = ""
    @State private var activityMenuTitle = "Деятельность"
    @State private var activityId: UInt8 = 0

    init(_ taskId: UInt16, stateManager: TaskInfoStateManager, viewModel: TaskInfoViewModel) {
        self.taskId = taskId
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Добавить трудозатрату")
        { ViewBody } action: {
            Task { await addLaborCost() }
        }
    }

    private var ViewBody: some View {
        LaborCostCreationForm
            .task { await viewModel.getActivityList() }
    }

    @ViewBuilder
    private var LaborCostCreationForm: some View {
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
            ForEach(viewModel.activityListSignal, id: \.id) { activity in
                Button(activity.name ?? "-") {
                    activityId = UInt8(activity.id ?? 0)
                    activityMenuTitle = activity.name ?? "-"
                }
            }
        } label: {
            HStack {
                Text(activityMenuTitle)

                Spacer()

                Image(systemName: "arrow.uturn.down.circle").scaleEffect(x: -1)
            }
        }.tint(.primary)
    }

    private func addLaborCost() async {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy"
        let created_at = dateFormatter.string(from: date)

        let hours_spent = spentTime.replacingOccurrences(of: ":", with: "")

        let taskIdInt32 = Int32(taskId)
        let taskid = KotlinInt(int: taskIdInt32)

        let activityIdInt32 = Int32(activityId)
        let activityid = KotlinInt(int: activityIdInt32)

        let laborCost = ManHoursDTO(id: nil,
                                    created_at: created_at,
                                    hours_spent: hours_spent,
                                    comment: note,
                                    taskid: taskid,
                                    projectid: nil,
                                    activityid: activityid)

        print(laborCost)

        await viewModel.createLaborCost(taskId, laborCost: laborCost)
        await viewModel.getTaskInfo(taskId)
        
        stateManager.isCreationAlertShown.toggle()
    }
}
