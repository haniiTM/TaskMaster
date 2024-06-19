//
//  TemplateScreenInfoButton.swift
//  iosApp
//
//  Created by evilgen on 17.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateScreenInfoButton<Content: View>: View {
    //    MARK: Props
    private let action: Openable
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(action: Openable, @ViewBuilder content: @escaping () -> Content) {
        self.action = action
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        HStack {
            //            Button(action: action.open) {
            HStack {
                content()
                    .foregroundColor(.black)
            }
            //            }
            .padding(8)
            .background(
                .white,
                in: RoundedRectangle(cornerRadius: 10, style: .continuous)
            )
        }
        .padding(.horizontal, ScreenInfoButtonsConstants.Numbers.externalPadding)
    }
}
