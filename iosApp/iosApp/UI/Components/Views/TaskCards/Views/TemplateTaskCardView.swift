//
//  TemplateTaskCardView.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateTaskCardView<Content: View>: View {
    //    MARK: Props
    private let action: () -> Void
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(action: @escaping () -> Void, @ViewBuilder content: @escaping () -> Content) {
        self.action = action
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        Button(action: action) {
            HStack {
                VStack(alignment: .leading, spacing: TaskCardsConstants.Numbers.lineSpacing) {
                    content()
                }

                Spacer()
            }
        }
        .padding()
        .border(.primary)
    }

    //    MARK: Methods
    private func removeCard() {}
}
