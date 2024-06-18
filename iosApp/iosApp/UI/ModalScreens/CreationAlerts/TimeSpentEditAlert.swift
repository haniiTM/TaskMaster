//
//  TimeSpentEditAlert.swift
//  TaskMaster
//
//  Created by  user on 06-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TimeEditAlert: View {
    @ObservedObject private var stateManager: TaskInfoStateManager
    @Binding private var text: String
    private let action: () async -> Void
    @State private var isEmpty = true

    init(_ text: Binding<String>,
         stateManager: TaskInfoStateManager,
         action: @escaping () async -> Void) {
        self._text = text
        self.stateManager = stateManager
        self.action = action
    }

    var body: some View {
        TemplateCreationAlert("Сохранить", $isEmpty)
        { ViewBody } action: {
            Task { await editSpentTime() }
        }
    }

    private var ViewBody: some View {
        CustomTextField(text,
                        $text)
        .onChange(of: text) { _ in checkIfEmpty() }
    }

    private func editSpentTime() async {
        await action()
        stateManager.isTimeEditAlertVisible.toggle()
    }

    private func checkIfEmpty() {
        isEmpty = text.isEmpty
    }
}
