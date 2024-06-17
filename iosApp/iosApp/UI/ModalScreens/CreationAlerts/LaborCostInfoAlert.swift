//
//  LaborCostInfoAlert.swift
//  TaskMaster
//
//  Created by  user on 28-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct LaborCostInfoAlert: View {
    @ObservedObject private var viewModel: LaborCostListViewModel
    @ObservedObject private var stateManager: LaborCostListStateManager

    private let taskId: UInt16
    private let laborCostId: UInt16
    @State private var note: String
    private let date: String
    private let spentTime: String

    init(_ taskId: UInt16,
         _ model: ManHoursDTO,
         _ stateManager: LaborCostListStateManager,
         _ viewModel: LaborCostListViewModel)
    {
        self.taskId = taskId
        laborCostId = model.id as? UInt16 ?? 0
        note = model.comment ?? "Нет комментариев"

        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        let date = dateFormatter.date(from: model.created_at ?? "??/??/????")

        let calendar = Calendar.current
        let dateComponents = calendar.dateComponents([.day, .month, .year], from: date ?? .init())

        let day = dateComponents.day?.description ?? "??"
        let month = dateComponents.month?.description ?? "??"
        let year = dateComponents.year?.description ?? "????"

        self.date = "\(day)/\(month)/\(year)"

        spentTime = model.hours_spent ?? "??:??"

        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Сохранить")
        { ViewBody } action: {
            Task { await saveChanges() }
        }
    }

    private var ViewBody: some View {
        LaborCostInfoView
    }

    @ViewBuilder
    private var LaborCostInfoView: some View {
        Text("Трудозатрата № \(laborCostId)")

        TextField(note, text: $note)

        HStack {
            Spacer()

            Text(date)

            Spacer()

            Rectangle()
                .frame(width: 1, height: 20)

            Spacer()

            Text(spentTime)

            Spacer()
        }
    }

    private func saveChanges() async {
        await viewModel.updateLaborCost(taskId, laborCostId, note)
        stateManager.isInfoAlertShown.toggle()
    }
}
