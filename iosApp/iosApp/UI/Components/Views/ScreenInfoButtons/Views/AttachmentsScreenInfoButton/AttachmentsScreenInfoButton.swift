//
//  AttachmentsScreenInfoButton.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct AttachmentsScreenInfoButton: View {
    //    MARK: Props
    private let title: String
    private let imageName: String
    private let action: Openable

    //    MARK: Init
    /// Initializes the view with specified parameters.
    /// - Parameters:
    ///   - title: A string representing the title of the view.
    ///   - action: A closure representing the action to be performed when the view is interacted with.
    init(_ title: String, imageName: String, action: Openable) {
        self.title = title
        self.imageName = imageName
        self.action = action
    }

    /// Initializes the view with default realization.
    init() {
        title = ScreenInfoButtonsConstants.Strings.Titles.attachmentsTitle
        imageName = "paperclip"
        action = AttachmentsScreenInfoButtonAction()
    }

    //    MARK: Body
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
