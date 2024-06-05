//
//  TaskListNavBar.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskListNavBar<Content: View, StateManager: UserListVisible>: View {
    @ObservedObject private var stateManager: StateManager
    @ViewBuilder private let content: () -> Content

    private let title: String
    private let viewModel: Searchable

    init(_ title: String,
         stateManager: StateManager,
         viewModel: Searchable,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.stateManager = stateManager
        self.viewModel = viewModel
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
        Button(action: { stateManager.isUserListVisible.toggle() }) {
            Label("Пользователи", systemImage: "person.crop.rectangle.stack")
        }
    }
}
