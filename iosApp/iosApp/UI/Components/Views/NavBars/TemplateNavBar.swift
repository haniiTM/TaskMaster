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
    @EnvironmentObject var themeManager: ThemeManager

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
            .navigationBarTitleDisplayMode(.inline)
        //            .toolbarRole(.editor)
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    Menu {
                        Button(action: { themeManager.isDarkThemeActive.toggle() }) {
                            Label("Сменить тему", systemImage: colorScheme == .dark ? "sun.min" : "moon.circle")
                        }

                        Button(action: {
                            Task { await viewModel.search() }
                        }) {
                            Label("Поиск", systemImage: Constants.Strings.ImageNames.searchActionImageName)
                        }

                        navBarItems()
                    } label: {
                        Image(systemName: Constants.Strings.ImageNames.extraActionsImageName)
                    }
                }
            }
    }
}
