//
//  ProjectFrameView.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectFrameView<Content: View>: View {
    //    MARK: Props
    private let title: String
    private let action: Openable = ProjectFrameAction()
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(_ title: String, @ViewBuilder content: @escaping () -> Content) {
        self.title = title
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        TemplateTaskFrame(title,
                          imageName: TaskFramesConstants.ImageNames.searchActionImageName,
                          action: action,
                          content: content)
    }
}
