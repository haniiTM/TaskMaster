//
//  TemplateTaskSectionBG.swift
//  iosApp
//
//  Created by evilgen on 17.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TemplateTaskSectionBG<Content: View>: View {
    //    MARK: Props
    private let title: String
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(_ title: String, @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        VStack(spacing: TaskSectionBGsConstants.Numbers.sectionComponentsVerticalSpacing) {
            Text(title)
                .font(.title3)

            VStack(spacing: TaskSectionBGsConstants.Numbers.contentComponentsVerticalSpacing) {
                content()
            }
        }
        .frame(maxWidth: .infinity)
        .padding()
        .background(
            .ultraThickMaterial,
            in: RoundedRectangle(cornerRadius: 8, style: .continuous).stroke(lineWidth: 4)
        )
    }
}
