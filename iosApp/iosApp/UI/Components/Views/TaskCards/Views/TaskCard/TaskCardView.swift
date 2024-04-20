//
//  TaskCardView.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskCardView: View {
    //    MARK: Props
    private let taskInfo: TaskInfo
    private let controller: TaskCardActions

    //    MARK: Init
    init(taskInfo: TaskInfo) {
        self.taskInfo = taskInfo
        controller = TaskCardController()
    }

    //    MARK: Body
    var body: some View {
        TemplateTaskCardView(controller: controller) { ViewBody }
    }

    @ViewBuilder
    private var ViewBody: some View {
        if let unwrappedNumberValue = taskInfo.numberValue {
            Text(TaskCardsConstants.Strings.numberTitle + unwrappedNumberValue.description)
                .font(.footnote)
        }

        Text(taskInfo.title)
            .multilineTextAlignment(.leading)

        HStack {
            Text(TaskCardsConstants.Strings.timerTitle(taskInfo.timerValue))
                .font(.footnote)
                .multilineTextAlignment(.leading)

            Spacer()

            if taskInfo.isUrgent {
                Image(systemName: TaskCardsConstants.ImageStrings.urgentImageName)
            }
        }

        if let unwrappedCategories = taskInfo.categories {
            Text(unwrappedCategories.joined(separator: TaskCardsConstants.Strings.categoriesSeparator))
                .font(.footnote)
        }
    }
}
