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

    @State private var isSearchPresented = false
    @Binding private var searchText: String
    private let title: String

    init(_ title: String,
         _ searchText: Binding<String>,
         @ViewBuilder content: @escaping () -> Content,
         @ViewBuilder navBarItems: @escaping () -> NavBarItems) {
        self.title = title
        self._searchText = searchText

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
                        Button {
                            themeManager.isDarkThemeActive.toggle()
                        } label: {
                            Label("Сменить тему",
                                  systemImage: colorScheme == .dark ? "sun.min" : "moon.circle")
                        }

                        Button {
                            isSearchPresented.toggle()
                        } label: {
                            Label("Поиск",
                                  systemImage: Constants.Strings.ImageNames.searchActionImageName)
                        }

                        navBarItems()
                    } label: {
                        Image(systemName: Constants.Strings.ImageNames.extraActionsImageName)
                            .padding()
                    }
                }
            }
            .if(isSearchPresented) { view in
                view.searchable(text: $searchText,
                                placement: .navigationBarDrawer(displayMode: .always))
            }
    }
}

extension View {
    @ViewBuilder func `if`<Content: View>(
        _ condition: Bool,
        transform: (Self) -> Content
    ) -> some View {
        if condition {
            transform(self)
        } else {
            self
        }
    }
}
