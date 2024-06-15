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
    private let stateManager: StateManager
    @Binding private var searchText: String

    private let title: String
    @ViewBuilder private let content: () -> Content

    // MARK: Init
    init(_ title: String,
         _ stateManager: StateManager,
         _ searchText: Binding<String>,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.stateManager = stateManager
        self._searchText = searchText
        self.content = content
    }

    // MARK: Body
    var body: some View {
        TaskListNavBar(title,
                       stateManager,
                       $searchText) {
            TemplateTaskFrame(content: content)
        }
    }
}
