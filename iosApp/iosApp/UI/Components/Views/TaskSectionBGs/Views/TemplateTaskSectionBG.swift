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
    private let isEmpty: Bool
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(_ title: String, isEmpty: Bool, @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.isEmpty = isEmpty
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
                if isEmpty {
                    Text("Задачи отсутствуют")
                        .frame(maxWidth: .infinity)
                        .padding(64)
                        .background(
                            Color(uiColor: .secondarySystemBackground),
                            in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                        )
                }

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
