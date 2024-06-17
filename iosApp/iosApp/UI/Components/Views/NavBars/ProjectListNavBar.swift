//
//  ProjectListNavBar.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectListNavBar<Content: View>: View {
    @EnvironmentObject var userRoleManager: UserRoleManager
    @ObservedObject private var stateManager: ProjectListStateManager
    @Binding private var searchText: String

    @ViewBuilder private let content: () -> Content
    private let title: String

    init(_ title: String,
         _ searchText: Binding<String>,
         _ stateManager: ProjectListStateManager,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self._searchText = searchText
        self.stateManager = stateManager
        self.content = content
    }

    var body: some View {
        TemplateNavBar(title,
                       $searchText,
                       content: content,
                       navBarItems: { ViewBody })
    }

    @ViewBuilder
    private var ViewBody: some View {
        if userRoleManager.isAdmin {
            Button(action: { stateManager.addUserState.toggle() }) {
                Label("Добавить пользователя", systemImage: "person.crop.circle.badge.plus")
            }

            Button(action: { stateManager.deleteUserState.toggle() }) {
                Label("Удалить пользователя", systemImage: "person.crop.circle.badge.minus")
            }

            Button(action: { stateManager.addProjectState.toggle() }) {
                Label("Добавить проект", systemImage: "plus.rectangle.on.rectangle")
            }
        }

        Button(role: .destructive) {
            stateManager.isLogOutAlertPresented = true
        } label: {
            Label("Выйти", systemImage: "rectangle.portrait.and.arrow.right")
                .tint(Color(uiColor: .systemPink))
        }
    }
}
