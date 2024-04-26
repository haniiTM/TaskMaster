//
//  TaskCreationButton.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskCreationButton: View {
    // MARK: Props
    private let title: String
    private let imageName: String
    private let action: Openable

    // MARK: Init
    /// Initializes the view with specified parameters.
    /// - Parameters:
    ///   - title: A string representing the title of the view.
    ///   - imageName: A string representing the name of the image to be displayed.
    ///   - action: A closure representing the action to be performed when the view is interacted with.
    init(_ title: String, imageName: String, action: Openable) {
        self.title = title
        self.imageName = imageName
        self.action = action
    }

    /// Initializes the view with default realization.
    init() {
        title = ActionButtonsConstants.Strings.Titles.taskTitle
        imageName = ActionButtonsConstants.Strings.ImageNames.taskImageName
        action = TaskCreationButtonAction()
    }

    //    MARK: Body
    var body: some View {
        TemplateActionButton(action: action) { ViewBody }
    }

    @ViewBuilder
    private var ViewBody: some View {
        Image(systemName: imageName)

        Text(title)
            .font(.subheadline)
    }
}
