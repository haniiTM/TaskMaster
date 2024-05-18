//
//  SubTaskSectionBG.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct SubTaskSectionBG<Content: View>: View {
    //    MARK: Props
    private let title: String
    private let isEmpty: Bool
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(isEmpty: Bool, @ViewBuilder content: @escaping () -> Content) {
        title = TaskSectionBGsConstants.Strings.subTaskSectionTitle
        self.isEmpty = isEmpty
        self.content = content
    }

    var body: some View {
        TemplateTaskSectionBG(title, isEmpty: isEmpty, content: content)
    }
}
