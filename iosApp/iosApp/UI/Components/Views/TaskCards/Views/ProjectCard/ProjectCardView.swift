//
//  ProjectCardView.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct ProjectCardView: View {
    //    MARK: Props
    private let controller: ProjectCardControllerProtocol

    //    MARK: Init
    init(model: ProjectInfo) {
        controller = ProjectCardController(model: model)
    }

    //    MARK: Body
    var body: some View {
        TemplateTaskCardView(controller: controller) { ViewBody }
    }

    @ViewBuilder
    private var ViewBody: some View {
        Text(controller.taskTitle)
            .multilineTextAlignment(.leading)

        HStack {
            Text(controller.timerTitle)
                .font(.subheadline)
                .multilineTextAlignment(.leading)

            Spacer()

//            if controller.isUrgent {
//                Image(systemName: controller.urgentImageName)
//            }
        }

        Text(controller.participiantsTitle)
            .font(.subheadline)
    }
}
