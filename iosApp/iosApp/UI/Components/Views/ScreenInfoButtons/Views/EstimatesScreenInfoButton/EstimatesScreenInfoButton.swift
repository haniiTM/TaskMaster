//
//  EstimatesScreenInfoButton.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct EstimatesScreenInfoButton: View {
    //    MARK: Props
    private let title: String
    private let action: Openable

    //    MARK: Init
    init() {
        title = ScreenInfoButtonsConstants.Strings.Titles.estimatesTitle
        action = EstimatesScreenInfoButtonAction()
    }

    //    MARK: Body
    var body: some View {
        AttachmentsScreenInfoButton(title, action: action)
    }
}
