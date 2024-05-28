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
    @ObservedObject private var stateManager: LaborCostListStateManager

    private let id: UInt16
    private let note: String
    private let date: String
    private let spentTime: String

    init(_ model: ManHoursDTO, stateManager: LaborCostListStateManager) {
        id = model.id as? UInt16 ?? 0
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
    }

    var body: some View {
        TemplateCreationAlert("Сохранить")
        { ViewBody } action: { saveChanges() }
    }

    private var ViewBody: some View {
        LaborCostInfoView
    }

    @ViewBuilder
    private var LaborCostInfoView: some View {
        Text("Трудозатрата № \(id)")

        Text(note)

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

    private func saveChanges() {
        stateManager.isInfoAlertShown.toggle()
    }
}
