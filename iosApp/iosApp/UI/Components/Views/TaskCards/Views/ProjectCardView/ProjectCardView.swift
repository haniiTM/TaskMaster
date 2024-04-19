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
    private let defaultAction: Openable = ProjectCardViewAction()
    private let taskInfo: TaskInfo

    //    MARK: Init
    init(taskInfo: TaskInfo) {
        self.taskInfo = taskInfo
    }

    //    MARK: Body
    var body: some View {
        TemplateTaskCardView(action: defaultAction.open) { ViewBody }
    }

    @ViewBuilder
    private var ViewBody: some View {
        Text(taskInfo.title)
            .multilineTextAlignment(.leading)

        HStack {
            Text(TaskCardsConstants.Strings.timerTitle(taskInfo.timerValue))
                .font(.subheadline)
                .multilineTextAlignment(.leading)

            Spacer()

            if taskInfo.isUrgent {
                Image(systemName: TaskCardsConstants.ImageStrings.urgentImageName)
            }
        }

        if let unwrappedParticipiantsValue = taskInfo.participiantsValue {
            Text(TaskCardsConstants.Strings.participiantsTitle + unwrappedParticipiantsValue.description)
                .font(.subheadline)
        }
    }
}
