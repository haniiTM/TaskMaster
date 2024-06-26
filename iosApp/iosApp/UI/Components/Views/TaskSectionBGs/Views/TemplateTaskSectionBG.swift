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
                .foregroundColor(.black)
                .font(.title3)

            VStack(spacing: TaskSectionBGsConstants.Numbers.contentComponentsVerticalSpacing) {
                if isEmpty {
                    Text("Задачи отсутствуют")
                        .foregroundColor(.black)
                        .multilineTextAlignment(.center)
                        .frame(maxWidth: .infinity)
                        .padding(48)
                        .background(
                            Color.shadowGray,
                            in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                        )
                }

                content()
            }

        }
        .frame(maxWidth: .infinity)
        .padding()
        .background(
            Color.gray,
            in: RoundedRectangle(cornerRadius: 25, style: .continuous)
        )
    }
}
