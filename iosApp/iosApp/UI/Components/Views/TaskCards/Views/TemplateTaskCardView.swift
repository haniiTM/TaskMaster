//
//  TemplateTaskCardView.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateTaskCardView<Content: View, ContextItems: View>: View {
    //    MARK: Props
    @ViewBuilder private let content: () -> Content
    @ViewBuilder private let contextItems: () -> ContextItems

    //    MARK: Init
    init(@ViewBuilder content: @escaping () -> Content,
         @ViewBuilder contextItems: @escaping () -> ContextItems) {
        self.content = content
        self.contextItems = contextItems
    }

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        VStack {
            HStack {
                VStack(alignment: .leading, spacing: TaskCardsConstants.Numbers.lineSpacing) {
                    content()
                }

                Spacer()
            }
            .padding()
            .background(
                Color(uiColor: .secondarySystemBackground),
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )
        }
        .contextMenu {
            contextItems()
        }
    }
}
