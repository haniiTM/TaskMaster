//
//  AttachmentCardView.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct AttachmentCardView: View {
    //    MARK: Props
    private let title: String
    private let imageName: String
    private let action: Openable

    //    MARK: Init
    init(_ title: String) {
        self.title = title
        imageName = Constants.Strings.ImageNames.extraActionsImageName
        action = AttachmentCreationButtonAction()
    }

    //    MARK: Body
    var body: some View {
        HStack {
            Text(title)

            Spacer()

            Button(action: { action.open() }) {
                Image(systemName: imageName)
            }
        }
        .padding()
        .tint(.primary)
        .background(
            Color(uiColor: .secondarySystemBackground),
            in: RoundedRectangle(cornerRadius: 8, style: .continuous)
        )
    }
}
