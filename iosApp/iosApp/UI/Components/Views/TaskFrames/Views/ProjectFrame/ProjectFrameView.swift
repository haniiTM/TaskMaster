//
//  ProjectFrameView.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectFrameView<Content: View, StateManager: UserListVisible>: View {
    // MARK: Props
    private let title: String
    private let stateManager: StateManager
    private let viewModel: Searchable
    @ViewBuilder private let content: () -> Content

    // MARK: Init
    init(_ title: String,
         stateManager: StateManager,
         viewModel: Searchable,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.stateManager = stateManager
        self.viewModel = viewModel
        self.content = content
    }

    // MARK: Body
    var body: some View {
        TaskListNavBar(title,
                       stateManager: stateManager,
                       viewModel: viewModel) {
            TemplateTaskFrame(content: content)
        }
    }
}
