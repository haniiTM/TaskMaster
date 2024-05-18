//
//  TemplateActionButton.swift
//  iosApp
//
//  Created by evilgen on 16.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateActionButton<Content: View>: View {
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
        Button(action: action.open) {
            HStack {
                content()
            }
        }
        .padding()
        .tint(.white)
        .background(
            .tint,
            in: RoundedRectangle(cornerRadius: 8, style: .continuous)
        )
    }
}
