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
    private let action: Openable = EstimatesScreenInfoButtonAction()

    //    MARK: Body
    var body: some View {
        AttachmentsScreenInfoButton(ScreenInfoButtonsConstants.Strings.estimatesTitle,
                                    action: action)
    }
}
