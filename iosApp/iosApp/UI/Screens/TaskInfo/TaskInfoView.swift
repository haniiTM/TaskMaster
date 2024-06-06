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
    @StateObject private var stateManager = TaskInfoStateManager()

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
                await viewModel.getTaskInfo(taskId)
            }
            .sheet(isPresented: $stateManager.isCreationAlertShown) {
                LaborCostCreationAlert(taskId, stateManager: stateManager, viewModel: viewModel)
            }
            .sheet(isPresented: $stateManager.isTimeSpentAlertVisible) {
                TimeSpentEditAlert(stateManager: stateManager, viewModel: viewModel)
            }
    }

    private var ViewBody: some View {
        ProjectFrameView(projectTitle,
                         stateManager: stateManager,
                         viewModel: viewModel) {
            TaskInfoCard

            LaborCostCreationButton(stateManager)
        }
    }

    private var TaskInfoCard: some View {
        VStack(spacing: 8) {
            VStack(spacing: 0) {
                Group {
                    Text(viewModel.taskInfo.title)
                    Divider()

                    ButtonRow(participantTitle,
                              viewModel.taskInfo.participiantsValue.description)
                    {}
                    Divider()

                    ButtonRow(hoursDaysSpentTitle,
                              viewModel.taskInfo.allocatedTime.description)
                    { stateManager.isTimeSpentAlertVisible.toggle() }
                    Divider()

                    TextRow(timeEstimationTitle, viewModel.taskInfo.timerValue.description)
                    Divider()

                    TextRow(timeSpentTitle, viewModel.taskInfo.spentTime)
                    Divider()

                    MenuRow(dependenceTitle,
                            viewModel.taskInfo.taskDependsOn.name)
                    { Text("hello there") }
                    Divider()

                    MenuRow(categoryTitle,
                            viewModel.taskInfo.categoryId.description.decodeCategory())
                    { Text("hello there") }
                    Divider()

                    MenuRow(taskStatusTitle,
                            viewModel.taskInfo.statusId.description.decodeTaskStatus())
                    { Text("hello there") }
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
    private func ButtonRow(_ leadingValue: String, _ trailingValue: String, action: @escaping () -> Void) -> some View {
        Button {
            action()
        } label: {
            TextRow(leadingValue, trailingValue)
        }
    }

    private func TextRow(_ leadingValue: String, _ trailingValue: String) -> some View {
        HStack {
            Text(leadingValue)

            Spacer()

            Text(trailingValue)
        }.foregroundColor(.primary)
    }
}

struct MenuRow<Content: View>: View {
    private let leadingValue: String
    private let trailingValue: String
    private let content: () -> Content

    init(_ leadingValue: String,
         _ trailingValue: String,
         content: @escaping () -> Content) {
        self.leadingValue = leadingValue
        self.trailingValue = trailingValue
        self.content = content
    }

    var body: some View {
        Menu {
            content()
        } label: {
            HStack {
                Text(leadingValue)

                Spacer()

                Text(trailingValue)
            }.foregroundColor(.primary)
        }
    }
}
