//
//  SubTaskCreationButton.swift
//  TaskMaster
//
//  Created by evilgen on 22.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct SubTaskCreationButton: View {
    //    MARK: Props
    private let title: String
    private let imageName: String
    private let action: Openable

    //    MARK: Init
    init() {
        title = ActionButtonsConstants.Strings.Titles.subTaskTitle
        imageName = ActionButtonsConstants.Strings.ImageNames.subTaskImageName
        action = SubTaskCreationButtonAction()
    }

    //    MARK: Body
    var body: some View {
        TaskCreationButton(title,
                           imageName: imageName,
                           action: action)
    }
}
