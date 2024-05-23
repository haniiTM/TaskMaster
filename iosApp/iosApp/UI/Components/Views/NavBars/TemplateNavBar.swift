//
//  TemplateNavBar.swift
//  TaskMaster
//
//  Created by  user on 19-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateNavBar<Content: View, NavBarItems: View>: View {
    @Environment(\.colorScheme) var colorScheme

    @ViewBuilder private let content: () -> Content
    @ViewBuilder private let navBarItems: () -> NavBarItems

    private let title: String
    private let viewModel: Searchable

    init(title: String,
         viewModel: Searchable,
         @ViewBuilder content: @escaping () -> Content,
         @ViewBuilder navBarItems: @escaping () -> NavBarItems) {
        self.title = title
        self.viewModel = viewModel

        self.content = content
        self.navBarItems = navBarItems
    }

    var body: some View {
        content()
            .navigationTitle(title)
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    Button(action: {}) {
                        Image(systemName: colorScheme == .dark ? "sun.min" : "moon")
                    }
                }

                ToolbarItem(placement: .topBarTrailing) {
                    Menu {
                        Button(action: {
                            Task { await viewModel.search() }
                        }) {
                            Label("Поиск", systemImage: "magnifyingglass.circle")
                        }

                        navBarItems()
                    } label: {
                        Image(systemName: Constants.Strings.ImageNames.extraActionsImageName)
                    }
                }
            }
    }
}
