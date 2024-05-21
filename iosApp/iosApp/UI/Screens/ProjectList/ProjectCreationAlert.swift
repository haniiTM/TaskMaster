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
        VStack {
            TextField("Название проекта", text: $text)

            Button("Создать проект") {
                Task {
                    await viewModel.createProject(text)
                    alertManager.addProjectState.toggle()
                }
            }
        }
    }
}
