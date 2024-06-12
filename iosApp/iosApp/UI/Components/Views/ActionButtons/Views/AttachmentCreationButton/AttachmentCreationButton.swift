//
//  AttachmentCreationButton.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct AttachmentCreationButton: View {
    //    MARK: Props
    private let title: String
    private let imageName: String
    private let action: Openable

    //    MARK: Init
    init(_ stateManager: AttachmentListStateManager) {
        title = ActionButtonsConstants.Strings.Titles.attachmentTitle
        imageName = ActionButtonsConstants.Strings.ImageNames.attachmentImageName
        action = AttachmentCreationButtonAction(stateManager)
    }

    //    MARK: Body
    var body: some View {
        TaskCreationButton(title,
                           imageName: imageName,
                           action: action)
    }
}
