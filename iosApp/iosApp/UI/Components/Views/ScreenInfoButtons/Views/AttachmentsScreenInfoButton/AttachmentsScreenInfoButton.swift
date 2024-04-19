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
    private let defaultAction: Openable = ScreenInfoButtonAction()

    private let title: String?
    private let action: (() -> Void)?

    //    MARK: Init
    /// Initializes the view with specified parameters.
    /// - Parameters:
    ///   - title: A string representing the title of the view.
    ///   - action: A closure representing the action to be performed when the view is interacted with.
    init(_ title: String, action: @escaping () -> Void) {
        self.title = title
        self.action = action
    }

    /// Initializes the view with default realization.
    init() {
        title = nil
        action = nil
    }

    //    MARK: Body
    var body: some View {
        TemplateScreenInfoButton(action: action ?? defaultAction.open) { ViewBody }
    }

    @ViewBuilder
    private var ViewBody: some View {
        Spacer()

        Text(title ?? ScreenInfoButtonsConstants.Strings.attachmentsTitle)
            .lineLimit(ScreenInfoButtonsConstants.Numbers.lineLimit)
            .minimumScaleFactor(ScreenInfoButtonsConstants.Numbers.minimumScaleFactor)

        Spacer()
    }
}
