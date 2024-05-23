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
    private let controller: TaskCardActions
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(controller: TaskCardActions, @ViewBuilder content: @escaping () -> Content) {
        self.controller = controller
        self.content = content
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
            Button(
                action: {
                    Task { await controller.remove() }
                }
            ) {
                Label("Удалить", systemImage: "trash.square")
            }
        }
    }
}
