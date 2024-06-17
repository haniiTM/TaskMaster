//
//  TaskListNavBar.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskListNavBar<Content: View, StateManager: UserListVisible>: View {
    @EnvironmentObject var userRoleManager: UserRoleManager
    @ObservedObject private var stateManager: StateManager
    @Binding private var searchText: String

    @ViewBuilder private let content: () -> Content
    private let title: String

    init(_ title: String,
         _ stateManager: StateManager,
         _ searchText: Binding<String>,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.stateManager = stateManager
        self._searchText = searchText
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
            Button(action: { stateManager.isUserListVisible.toggle() }) {
                Label("Пользователи", systemImage: "person.crop.rectangle.stack")
            }
        }
    }
}
