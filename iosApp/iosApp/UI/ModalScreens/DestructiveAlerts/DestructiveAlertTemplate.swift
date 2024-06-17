//
//  DestructiveAlertTemplate.swift
//  TaskMaster
//
//  Created by  user on 14-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct DestructiveAlertTemplate {
    private let title: String
    private let message: String

    private let primaryButtonTitle: String
    private let secondaryButtonTitle: String

    private let primaryButtonAction: () -> Void
    private let secondaryButtonAction: () -> Void

    init(_ title: String,
         primaryButtonAction: @escaping () -> Void,
         secondaryButtonAction: @escaping () -> Void)
    {
        self.title = title
        self.primaryButtonAction = primaryButtonAction
        self.secondaryButtonAction = secondaryButtonAction

        message = Constants.Strings.DestructiveAlertMessage.warningTitle
        primaryButtonTitle = Constants.Strings.DestructiveAlertMessage.confirmationActionTitle
        secondaryButtonTitle = Constants.Strings.DestructiveAlertMessage.denialActionTitle
    }

    var body: Alert {
        .init(
            title: Text(title),
            message: Text(message),
            primaryButton: .destructive(Text(primaryButtonTitle)) { primaryButtonAction() },
            secondaryButton: .cancel(Text(secondaryButtonTitle)) { secondaryButtonAction() }
        )
    }
}
