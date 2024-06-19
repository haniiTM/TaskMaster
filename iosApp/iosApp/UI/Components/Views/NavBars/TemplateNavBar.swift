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
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .principal) {
                    HStack {
                        Button {
                            themeManager.isDarkThemeActive.toggle()
                        } label: {
                            colorScheme == .dark
                            ? Image.lightThemeIcon
                            : Image.darkThemeIcon
                        }

                        Spacer()

                        Text(title)
                            .font(.headline)

                        Spacer()

                        HStack(spacing: 0) {
                            Button {
                                isSearchPresented.toggle()
                            } label: {
                                Image.search1Icon
                            }

                            Menu {
                                navBarItems()
                            } label: {
                                Image.more
                            }
                        }
                    }
                    .foregroundColor(.black)
                    .padding(.horizontal)
                    .background(Color.white)
                    .cornerRadius(10)
                    .overlay(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(Color.black, lineWidth: 1)
                    )
                    .frame(height: 44)
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
