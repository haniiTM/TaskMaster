//
//  LaborCostCreationButton.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct LaborCostCreationButton: View {
    //    MARK: Props
    private let defaultAction: Openable = LaborCostCreationButtonAction()

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
        TemplateActionButton(action: action ?? defaultAction.open) { ViewBody }
    }

    @ViewBuilder
    private var ViewBody: some View {
        Text(title ?? ActionButtonsConstants.Strings.laborCostTitle)
            .font(.subheadline)
    }
}
