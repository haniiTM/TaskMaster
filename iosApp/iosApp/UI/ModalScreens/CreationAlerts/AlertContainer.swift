//
//  AlertContainer.swift
//  TaskMaster
//
//  Created by  user on 19-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct AlertContainer<Content: View>: View {
    private let content: Content
    @Binding private var isPresented: Bool

    init(_ isPresented: Binding<Bool>,
        @ViewBuilder content: () -> Content) {
        self._isPresented = isPresented
        self.content = content()
    }

    var body: some View {
        VStack {
            Spacer()

            content
                .shadow(radius: 10)

            Spacer()
        }
        .background(Color.black.opacity(0.6).edgesIgnoringSafeArea(.all).onTapGesture{ isPresented = false })
    }
}
