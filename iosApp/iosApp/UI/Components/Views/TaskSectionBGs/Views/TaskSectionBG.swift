//
//  TaskSectionBG.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskSectionBG<Content: View>: View {
    //    MARK: Props
    private let title: String
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(@ViewBuilder content: @escaping () -> Content) {
        title = TaskSectionBGsConstants.Strings.taskSectionTitle
        self.content = content
    }

    var body: some View {
        TemplateTaskSectionBG(title, content: content)
    }
}
