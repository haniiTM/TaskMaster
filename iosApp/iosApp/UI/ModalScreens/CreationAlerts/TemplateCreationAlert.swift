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
        VStack(spacing: 0) {
            Text(title)
                .foregroundColor(.white)
                .frame(maxWidth: .infinity)
                .padding(10)
                .background(GradientBG())
                .clipShape(TopRoundedCorners(radius: 20))

            Divider().background(.secondary)

            VStack(spacing: 0) {
                Group {
                    content()
                }
                .frame(maxWidth: .infinity, alignment: .topLeading)
                .padding(10)
                .background(.white)
            }

            Divider().background(.secondary)

            Button {
                if !isEmpty { action() }
            } label: {
                Text(buttonTitle)
                    .frame(maxWidth: .infinity)
                    .padding(12)
                    .padding(.horizontal)
                    .foregroundColor(isEmpty ? .gray : .black)
                    .background(isEmpty ? .gray : .white)
                    .clipShape(BottomRoundedCorners(radius: 20))
            }
        }
        .padding(.horizontal)
    }
}
