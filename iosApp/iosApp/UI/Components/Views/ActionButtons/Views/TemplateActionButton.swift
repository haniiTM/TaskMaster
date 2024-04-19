//
//  TemplateActionButton.swift
//  iosApp
//
//  Created by evilgen on 16.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateActionButton<Content: View>: View {
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
                content()
            }
        }
        .padding()
        .border(.primary)
    }
}
