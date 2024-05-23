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
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        ScrollView {
            VStack(spacing: TaskFramesConstants.Numbers.componentsVerticalSpacing) {
                content()
            }
            .frame(maxWidth: .infinity)
            .padding()
            .padding(.vertical)
        }
    }
}
