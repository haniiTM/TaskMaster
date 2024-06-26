//
//  ScreenInfoButton.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ScreenInfoButton: View {
    // MARK: Props
    private let isUrgent: Bool

    private let title: String
    private let urgentImageName: String
    private let imageName: String

    private let action: Openable

    // MARK: Init
    /// Initializes the view with specified parameters.
    /// - Parameters:
    ///   - title: A string representing the title of the view.
    ///   - imageName: A string representing the name of the image to be displayed.
    ///   - action: A closure representing the action to be performed when the view is interacted with.
    init(_ title: String, isUrgent: Bool, urgentImageName: String, imageName: String, action: Openable) {
        self.title = title
        self.isUrgent = isUrgent
        self.urgentImageName = urgentImageName
        self.imageName = imageName
        self.action = action
    }

    /// Initializes the view with default realization.
    init(_ title: String, isUrgent: Bool) {
        self.title = title
        self.isUrgent = isUrgent

        urgentImageName = TaskCardsConstants.Strings.ImageNames.urgentImageName
        imageName = ScreenInfoButtonsConstants.Strings.ImageNames.infoImageName
        action = ScreenInfoButtonAction()
    }

    // MARK: Body
    var body: some View {
        TemplateScreenInfoButton(action: action) { ViewBody }
    }

    @ViewBuilder
    private var ViewBody: some View {
        Text(title)
            .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)
            .minimumScaleFactor(ScreenInfoButtonsConstants.Numbers.minimumScaleFactor)

        Spacer()

        Image(systemName: imageName)
    }
}
