//
//  TimeSpentEditAlert.swift
//  TaskMaster
//
//  Created by  user on 06-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TimeSpentEditAlert: View {
    @ObservedObject private var viewModel: TaskInfoViewModel
    @ObservedObject private var stateManager: TaskInfoStateManager
    @State private var text = "0"

    init(stateManager: TaskInfoStateManager, viewModel: TaskInfoViewModel) {
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Сохранить")
        { ViewBody } action: {
            Task { await editSpentTime(text) }
        }
    }

    private var ViewBody: some View {
        TextField(text: $text) {
            Text(text)
                .padding()
        }
    }

    private func editSpentTime(_ text: String) async {
        stateManager.isTimeSpentAlertVisible.toggle()
    }
}
