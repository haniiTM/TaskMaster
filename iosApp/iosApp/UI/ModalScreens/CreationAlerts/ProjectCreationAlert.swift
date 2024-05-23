//
//  ProjectCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectCreationAlert: View {
    @ObservedObject private var viewModel: ProjectListViewModel
    @ObservedObject private var alertManager: ProjectListAlertManager
    @State private var text = ""

    init(alertManager: ProjectListAlertManager, viewModel: ProjectListViewModel) {
        self.alertManager = alertManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Создать проект")
        { ViewBody } action: {
            Task { await addProject(text) }
        }
    }

    private var ViewBody: some View {
        TextField(text: $text) {
            Text("Название проекта")
                .padding()
        }
    }

    private func addProject(_ title: String) async {
        await viewModel.createProject(title)
        alertManager.addProjectState.toggle()
    }
}
