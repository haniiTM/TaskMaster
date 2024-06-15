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
    @ObservedObject private var stateManager: ProjectListStateManager

    private let title: String
    private let viewModel: Searchable

    init(title: String,
         viewModel: Searchable,
         alertManager: ProjectListStateManager,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.viewModel = viewModel
        self.stateManager = alertManager
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
        Button(action: { stateManager.addUserState.toggle() }) {
            Label("Добавить пользователя", systemImage: "person.crop.circle.badge.plus")
        }

        Button(action: { stateManager.deleteUserState.toggle() }) {
            Label("Удалить пользователя", systemImage: "person.crop.circle.badge.minus")
        }

        Button(action: { stateManager.addProjectState.toggle() }) {
            Label("Добавить проект", systemImage: "plus.rectangle.on.rectangle")
        }

        Button(role: .destructive) {
            stateManager.isLogOutAlertPresented = true
        } label: {
            Label("Выйти", systemImage: "rectangle.portrait.and.arrow.right")
                .tint(Color(uiColor: .systemPink))
        }
    }
}
