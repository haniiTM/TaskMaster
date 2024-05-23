//
//  TaskListNavBar.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskListNavBar<Content: View>: View {
    @ViewBuilder private let content: () -> Content

    private let title: String
    private let viewModel: Searchable

    init(title: String,
         viewModel: Searchable,
         @ViewBuilder content: @escaping () -> Content) {
        self.title = title
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
        Button(action: {}) {
            Label("Пользователи", systemImage: "person.crop.rectangle.stack")
        }
    }
}
