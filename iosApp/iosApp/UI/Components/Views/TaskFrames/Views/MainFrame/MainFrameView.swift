//
//  MainFrameView.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct MainFrameView<Content: View>: View {
    //    MARK: Props
    private let action: Openable = MainFrameAction()
    @ViewBuilder private let content: () -> Content

    //    MARK: Init
    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    //    MARK: Body
    var body: some View {
        TemplateTaskFrame(TaskFramesConstants.Strings.Titles.mainFrameTitle,
                          imageName: Constants.Strings.ImageNames.extraActionsImageName,
                          action: action,
                          content: content)
    }
}
