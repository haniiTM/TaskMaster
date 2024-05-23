//
//  TaskInfoView.swift
//  TaskMaster
//
//  Created by evilgen on 25.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskInfoView: View {
    //    MARK: Props
    @StateObject private var viewModel = TaskInfoViewModel()

    private let taskId: UInt16

    private let projectTitle: String
    private let taskTitle: String

    private let participantTitle: String
    private let participantValue: String

    private let hoursDaysSpentTitle: String
    //    private let urgentImageName: String
    private let hoursDaysSpentValue: String

    private let timeEstimationTitle: String
    private let timeEstimationValue: String

    private let timeSpentTitle: String
    private let timeSpentValue: String

    private let dependenceTitle: String
    private let dependenceValue: String

    private let categoryTitle: String
    private let categoryValue: String

    private let taskStatusTitle: String
    private let taskStatusValue: String

    private let laborCostTitle: String

    //    MARK: Init
    init(_ projectTitle: String, taskId: UInt16) {
        let model = DetailedTaskInfo()

        self.taskId = taskId
        self.projectTitle = projectTitle
        taskTitle = model.title

        participantTitle = TaskCardsConstants.Strings.Titles.participiantsTitle
        //        (model.participiantsValue.description)
        participantValue = model.participiantsValue.description
        //        participantAvatar = "person.crop.circle"

        hoursDaysSpentTitle = "Затрачиваемые часы / день"
        //        urgentImageName = TaskCardsConstants.Strings.ImageNames.urgentImageName
        hoursDaysSpentValue = model.allocatedTime.description

        timeEstimationTitle = "Оценка времени:"
        timeEstimationValue = model.timerValue.description

        timeSpentTitle = "Затрачено времени:"
        timeSpentValue = model.spentTime.description

        dependenceTitle = "Зависит от:"
        dependenceValue = model.taskDependsOn.name

        categoryTitle = "Категория:"
        categoryValue = model.categoryId.description

        taskStatusTitle = "Статус задачи:"
        taskStatusValue = model.statusId.description

        laborCostTitle = "Трудозатраты"
    }

    //    MARK: Body
    var body: some View {
        ViewBody
            .task {
                await viewModel.updateDataSource(taskId)
            }
    }

    private var ViewBody: some View {
        ProjectFrameView(projectTitle, viewModel: viewModel) {
            TaskInfoCard

            LaborCostCreationButton()
        }
    }

    private var TaskInfoCard: some View {
        VStack(spacing: 8) {
            VStack(spacing: 0) {
                Group {
                    Text(viewModel.taskInfo.title)
                    Divider()

                    TextRow(participantTitle, viewModel.taskInfo.participiantsValue.description)
                    Divider()

                    TextRow(hoursDaysSpentTitle, viewModel.taskInfo.allocatedTime.description)
                    Divider()

                    TextRow(timeEstimationTitle, viewModel.taskInfo.timerValue.description)
                    Divider()

                    TextRow(timeSpentTitle, viewModel.taskInfo.spentTime)
                    Divider()

                    TextRow(dependenceTitle, viewModel.taskInfo.taskDependsOn.name)
                    Divider()

                    TextRow(categoryTitle, viewModel.taskInfo.categoryId.description)
                    Divider()

                    TextRow(taskStatusTitle, viewModel.taskInfo.statusId.description)
                }.padding(.top, 8)
            }
            .padding(8)
            .background(
                .ultraThickMaterial,
//                Color(uiColor: .secondarySystemBackground),
                in: RoundedRectangle(cornerRadius: 8, style: .continuous)
            )

            NavigationLink(destination: LaborCostListView(projectTitle, taskId: taskId)) {
                //                    Button(laborCostTitle) {}
                Text(laborCostTitle)
                    .frame(maxWidth: .infinity)
                    .padding(8)
                    .background(
                        Color(uiColor: .secondarySystemBackground),
//                        ultraThickMaterial,
                        in: RoundedRectangle(cornerRadius: 8, style: .continuous)
                    )
            }.tint(.primary)
        }.padding()
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
