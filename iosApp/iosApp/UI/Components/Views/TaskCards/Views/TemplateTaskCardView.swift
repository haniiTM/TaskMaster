//
//  TemplateTaskCardView.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

enum CardType {
    case task
    case project
}

struct TemplateTaskCardView<Content: View>: View {
    //    MARK: Props
    private let cardType: CardType
    private let leadingAction: () -> Void
    private let trailingAction: () -> Void
    @ViewBuilder private let content: () -> Content

    @State private var offset: CGFloat = 0
    @State private var backgroundColor: Color = .white
    @State private var icon: String? = nil

    //    MARK: Init
    init(_ cardType: CardType,
         leadingAction: @escaping () -> Void,
         trailingAction: @escaping () -> Void,
         @ViewBuilder content: @escaping () -> Content) {
        self.cardType = cardType
        self.leadingAction = leadingAction
        self.trailingAction = trailingAction
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
                        .foregroundColor(.black)
                }

                Spacer()
            }
            .padding()
            .background(
                backgroundColor
                    .cornerRadius(25)
                    .shadow(radius: 5)
            )
            .overlay(
                Group {
                    if let icon = icon {
                        Image(icon)
                            .foregroundColor(.white)
                            .font(.system(size: 24))
                            .padding()
                            .background(Color.black.opacity(0.6))
                            .clipShape(Circle())
                            .padding(.horizontal)
                    }
                }
                    .frame(maxWidth: .infinity, alignment: icon == "done_icon" ? .leading : .trailing)
                    .offset(x: offset)
                    .animation(.easeInOut, value: offset)
            )
            .offset(x: offset)
            .gesture(
                DragGesture()
                    .onChanged { value in
                        if (cardType == .task && value.translation.width > 10) || value.translation.width < -10 {
                            offset = value.translation.width
                            if offset > 0 {
                                backgroundColor = Color.green.opacity(Double(min(offset / 150, 1)))
                                icon = "done_icon"
                            } else {
                                backgroundColor = Color.red.opacity(Double(min(-offset / 150, 1)))
                                icon = "delete_icon"
                            }
                        }
                    }
                    .onEnded { value in
                        withAnimation {
                            if cardType == .task || value.translation.width < 0 {
                                if abs(offset) > 150 {
                                    if offset > 0 {
                                        leadingAction()
                                    } else {
                                        trailingAction()
                                    }

                                    offset = 0
                                    backgroundColor = .white
                                    icon = nil
                                } else {
                                    offset = 0
                                    backgroundColor = .white
                                    icon = nil
                                }
                            } else {
                                offset = 0
                                backgroundColor = .white
                                icon = nil
                            }
                        }
                    }
            )
        }
    }
}
