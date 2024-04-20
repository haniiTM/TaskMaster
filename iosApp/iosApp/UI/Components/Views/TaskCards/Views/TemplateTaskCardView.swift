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
    @State private var isButtonVisible = true

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
            if isButtonVisible {
                Button(action: controller.open) {
                    HStack {
                        VStack(alignment: .leading, spacing: TaskCardsConstants.Numbers.lineSpacing) {
                            content()
                        }

                        Spacer()
                    }
                }
                .padding()
                .border(.primary)
                .transition(.move(edge: .trailing))
                .animation(.easeInOut)
            }
        }
        .gesture(
            DragGesture()
                .onEnded { value in
                    if value.translation.width < -100 {
                        withAnimation {
                            isButtonVisible = false
                            controller.remove()
                        }
                    }
                }
        )
    }
}
