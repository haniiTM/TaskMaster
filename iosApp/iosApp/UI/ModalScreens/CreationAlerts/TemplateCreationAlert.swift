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
    private let buttonTitle: String
    private let action: () -> Void
    @Binding private var isEmpty: Bool

    init(_ title: String,
         _ buttonTitle: String,
         _ isEmpty: Binding<Bool>,
         @ViewBuilder content: @escaping () -> Content,
         action: @escaping () -> Void) {
        self.title = title
        self.buttonTitle = buttonTitle
        self._isEmpty = isEmpty
        self.content = content
        self.action = action
    }

    var body: some View {
        VStack {
            VStack {
                Text(title)

                Divider().background(.secondary)

                VStack {
                    Group {
                        content()
                    }
                    .frame(maxWidth: .infinity, alignment: .topLeading)
                    .padding()
                    .background(
                        .secondary.opacity(0.4),
                        in: RoundedRectangle(cornerRadius: 8, style: .continuous).stroke()
                    )
                }

                Divider().background(.secondary)

                Button {
                    if !isEmpty { action() }
                } label: {
                    Text(buttonTitle)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .padding(.horizontal)
                        .foregroundColor(.white)
                        .background(isEmpty ? Color.secondary : Color.accentColor,
                                    in: RoundedRectangle(cornerRadius: 8, style: .continuous))
                }
            }
            .padding()
            .background(
                Color(uiColor: .secondarySystemBackground),
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )
        }
        .padding(.horizontal)
    }
}
