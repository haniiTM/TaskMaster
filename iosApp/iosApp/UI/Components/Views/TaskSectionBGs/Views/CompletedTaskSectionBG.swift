//
//  CompletedTaskSectionBG.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct CompletedTaskSectionBG<Content: View>: View {
    //    MARK: Props
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    var body: some View {
        TemplateTaskSectionBG(TaskSectionBGsConstants.Strings.completedTaskSectionTitle, content: content)
    }
}
