//
//  TemplateTaskFrame.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateTaskFrame<Content: View>: View {
    //    MARK: Props
    @Environment(\.colorScheme) var colorScheme
    @State private var statusBarHeight: CGFloat = .init()
    @State private var frameBodyHeight: CGFloat = .init()
    @ViewBuilder private let content: () -> Content

    private let title: String
    private let imageName: String
    private let action: Openable

    //    MARK: Init
    init(_ title: String, imageName: String, action: Openable, @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.imageName = imageName
        self.action = action
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        //        NavBarView {
        ViewBody
        //        }
            .navigationTitle(title)
            .navigationBarBackButtonHidden()
            .toolbar {
                ToolbarItem(placement: .topBarLeading) {
                    Button(action: {}) {
                        Image(systemName: colorScheme == .dark ? "sun.min" : "moon")
                    }
                }

                ToolbarItem(placement: .topBarTrailing) {
                    Button(action: {}) {
                        Image(systemName: Constants.Strings.ImageNames.extraActionsImageName)
                    }
                }
            }
    }

    private var ViewBody: some View {
        FrameBody
            .overlay {
                ContentBody
            }
    }

    private var FrameBody: some View {
        VStack {
            HStack {
                Spacer()

                //                Text(title)

                //                Spacer()

                //                Button(action: action.open) {
                //                    Image(systemName: imageName)
                //                }
            }
            .padding()
            .background(GeometryReader { geo in
                Color.clear
                    .onAppear {
                        frameBodyHeight = geo.size.height
                        statusBarHeight = geo.safeAreaInsets.top
                    }
            })

            Spacer()
        }
    }

    private var ContentBody: some View {
        ScrollView {
            VStack(spacing: TaskFramesConstants.Numbers.componentsVerticalSpacing) {
                content()
            }
            .frame(maxWidth: .infinity)
            .padding()
            .padding(.vertical)
        }
        //        .border(.secondary)
        //        .background(GradientBG())
        .padding(.top, statusBarHeight - frameBodyHeight)
    }
}
