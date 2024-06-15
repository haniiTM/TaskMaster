//
//  MainFrameView.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct MainFrameView<Content: View>: View {
    //    MARK: Props
    @Binding private var searchText: String
    private let stateManager: ProjectListStateManager
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(_ searchText: Binding<String>,
         _ stateManager: ProjectListStateManager,
         @ViewBuilder content: @escaping () -> Content) {
        self._searchText = searchText
        self.stateManager = stateManager
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        NavigationView {
            ProjectListNavBar(TaskFramesConstants.Strings.Titles.mainFrameTitle,
                              $searchText,
                              stateManager)
            { TemplateTaskFrame(content: content) }
        }
    }
}
