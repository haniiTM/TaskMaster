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
    @ViewBuilder private let content: () -> Content
    private let viewModel: Searchable
    private let alertManager: ProjectListStateManager

    //    MARK: Init
    init(viewModel: Searchable, alertManager: ProjectListStateManager, @ViewBuilder content: @escaping () -> Content) {
        self.viewModel = viewModel
        self.alertManager = alertManager
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        NavigationView {
            ProjectListNavBar(title: TaskFramesConstants.Strings.Titles.mainFrameTitle,
                              viewModel: viewModel,
                              alertManager: alertManager) {
                TemplateTaskFrame(content: content)
            }
        }
    }
}
