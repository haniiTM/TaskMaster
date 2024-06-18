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
    @ObservedObject private var alertManager: ProjectListStateManager

    @State private var text = ""
    @State private var isEmpty = true

    init(alertManager: ProjectListStateManager, viewModel: ProjectListViewModel) {
        self.alertManager = alertManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Создать проект",
                              $isEmpty)
        { ViewBody } action: {
            Task { await addProject() }
        }
    }

    private var ViewBody: some View {
        CustomTextField("Название проекта",
                        $text)
        .onChange(of: text) { _ in checkIfEmpty() }
    }

    private func addProject() async {
        await viewModel.createProject(text)
        alertManager.addProjectState.toggle()
    }

    private func checkIfEmpty() {
        isEmpty = text.isEmpty
    }
}
