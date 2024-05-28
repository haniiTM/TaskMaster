//
//  TemplateCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 22-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateCreationAlert<Content: View>: View {
    @ViewBuilder private let content: () -> Content
    private let title: String
    private let action: () -> Void

    init(_ title: String, @ViewBuilder content: @escaping () -> Content, action: @escaping () -> Void) {
        self.title = title
        self.content = content
        self.action = action
    }

    var body: some View {
        VStack {
            VStack(spacing: 64) {
                VStack {
                    Group {
                        content()
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(
                        .secondary.opacity(0.4),
                        in: RoundedRectangle(cornerRadius: 8, style: .continuous).stroke()
                    )
                }

                VStack {
                    Divider().background(.secondary)

                    Button(action: action) {
                        Text(title)
                            .frame(maxWidth: .infinity)
                            .padding()
                            .padding(.horizontal)
                            .foregroundColor(.white)
                            .background(.tint,
                                        in: RoundedRectangle(cornerRadius: 8, style: .continuous))
                    }
                }
            }
            .padding()
            .background(
                Color(uiColor: .secondarySystemBackground),
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )
        }
    }
}
