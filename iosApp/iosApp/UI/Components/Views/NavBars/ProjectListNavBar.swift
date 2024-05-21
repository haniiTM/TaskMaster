//
//  ProjectListNavBar.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectListNavBar<Content: View>: View {
    @ViewBuilder private let content: () -> Content
    @ObservedObject private var alertManager: ProjectListAlertManager

    private let title: String
    private let viewModel: Searchable

    init(title: String,
         viewModel: Searchable,
         alertManager: ProjectListAlertManager,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.viewModel = viewModel
        self.alertManager = alertManager
        self.content = content
    }

    var body: some View {
        TemplateNavBar(title: title,
                       viewModel: viewModel,
                       content: content,
                       navBarItems: { ViewBody })
    }

    @ViewBuilder
    private var ViewBody: some View {
        Button(action: {}) {
            Label("Добавить пользователя", systemImage: "person.crop.circle.badge.plus")
        }

        Button(action: { alertManager.addProjectState.toggle() }) {
            Label("Добавить проект", systemImage: "plus.rectangle.on.rectangle")
        }
    }
}
