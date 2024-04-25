//
//  TaskInfoView.swift
//  TaskMaster
//
//  Created by evilgen on 25.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskInfoListView: View {
    //    MARK: Props
    private let projectTitle: String
    private let taskTitle: String

    private let participantTitle: String
    private let participantAvatar: String

    private let isUrgent: Bool
    private let hoursDaysSpentTitle: String
    private let urgentImageName: String
    private let hoursDaysSpentValue: String

    private let timeEstimationTitle: String
    private let timeEstimationValue: String

    private let timeSpentTitle: String
    private let timeSpentValue: String

    private let taskStatusTitle: String
    private let taskStatusValue: String

    private let laborCostTitle: String

    //    MARK: Init
    init(_ projectTitle: String, model: TaskInfo) {
        self.projectTitle = projectTitle
        taskTitle = model.title

        participantTitle = TaskCardsConstants.Strings.Titles.participiantsTitle +
        (model.participiantsValue?.description ?? TaskCardsConstants.Strings.EmptyTitles.emptyStringTitle)
        participantAvatar = "person.crop.circle"

        isUrgent = model.isUrgent
        hoursDaysSpentTitle = "Затрачиваемые часы / день"
        urgentImageName = TaskCardsConstants.Strings.ImageNames.urgentImageName
        hoursDaysSpentValue = "2:00"

        timeEstimationTitle = "Оценка времени:"
        timeEstimationValue = model.timerValue.description

        timeSpentTitle = "Затрачено времени:"
        timeSpentValue = "0:30"

        taskStatusTitle = "Статус задачи:"
        taskStatusValue = "В работе"

        laborCostTitle = "Трудозатраты"
    }

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        ProjectFrameView(projectTitle) {
            TaskInfoCard

            LaborCostCreationButton()
        }
    }

    private var TaskInfoCard: some View {
        VStack(spacing: 0) {
            Group {
                Text(taskTitle)
                Divider()

                ImageRow(participantTitle, participantAvatar)
                Divider()

                if isUrgent{
                    ImageRow(hoursDaysSpentTitle, urgentImageName)
                } else {
                    TextRow(hoursDaysSpentTitle, hoursDaysSpentValue)
                }
                Divider()

                TextRow(timeEstimationTitle, timeEstimationValue)
                Divider()

                TextRow(timeSpentTitle, timeSpentValue)
                Divider()

                TextRow(taskStatusTitle, taskStatusValue)
                Divider()

                Button(laborCostTitle) {}
            }.padding(.bottom, 8)
        }
        .padding()
        .border(.secondary)
    }

    //    MARK: Methods
    private func TextRow(_ leadingValue: String, _ trailingValue: String) -> some View {
        HStack {
            Text(leadingValue)

            Spacer()

            Text(trailingValue)
        }
    }

    private func ImageRow(_ leadingValue: String, _ trailingValue: String) -> some View {
        HStack {
            Text(leadingValue)

            Spacer()

            Image(systemName: trailingValue)
        }
    }
}
