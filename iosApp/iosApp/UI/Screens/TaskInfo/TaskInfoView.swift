//
//  TaskInfoView.swift
//  TaskMaster
//
//  Created by evilgen on 25.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct TaskInfoView: View {
    //    MARK: Props
    @StateObject private var viewModel = TaskInfoViewModel()
    @StateObject private var stateManager = TaskInfoStateManager()

    @State private var action: () async -> Void = {}
    @State private var timeEditText = ""
    @State private var searchText = ""

    private let projectId: UInt16
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
    init(_ projectTitle: String,
         projectId: UInt16,
         taskId: UInt16) {
        let model = DetailedTaskInfo()

        self.projectId = projectId
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
            .sheet(isPresented: $stateManager.isUserListVisible) {
                UserListAlert(taskId, stateManager: stateManager, viewModel: viewModel)
            }
            .sheet(isPresented: $stateManager.isUserAdditionAlertVisible) {
                UserListAdditionAlert(taskId,
                                      stateManager: stateManager,
                                      viewModel: viewModel)
            }
            .sheet(isPresented: $stateManager.isTimeEditAlertVisible) {
                TimeEditAlert($timeEditText,
                              stateManager: stateManager)
                { await action() }
            }
    }

    private var ViewBody: some View {
        ProjectFrameView(projectTitle,
                         stateManager,
                         $searchText) {
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

                    ButtonRowView

                    TextRow(timeSpentTitle, viewModel.taskInfo.spentTime)
                    Divider()

                    MenuRowView
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

    @ViewBuilder
    private var ButtonRowView: some View {
        ButtonRow(participantTitle,
                  viewModel.taskInfo.participiantsValue.description)
        { stateManager.isUserListVisible.toggle() }
        Divider()

        ButtonRow(hoursDaysSpentTitle,
                  viewModel.taskInfo.allocatedTime.description)
        {
            timeEditText = viewModel.taskInfo.allocatedTime.description
            action = {
                await viewModel.updateHoursSpent(taskId, hours: timeEditText)
                await viewModel.getTaskInfo(taskId)
            }

            stateManager.isTimeEditAlertVisible.toggle()
        }
        Divider()

        ButtonRow(timeEstimationTitle,
                  viewModel.taskInfo.timerValue.description)
        {
            timeEditText = viewModel.taskInfo.timerValue.description
            action = {
                await viewModel.updateEstimatedTime(taskId, hours: timeEditText)
                await viewModel.getTaskInfo(taskId)
            }
            stateManager.isTimeEditAlertVisible.toggle()
        }
        Divider()
    }

    @ViewBuilder
    private var MenuRowView: some View {
        MenuRow(
            dependenceTitle,
            viewModel.taskInfo.taskDependsOn.name,
            action: {
                await viewModel.updateTaskListForDependency(projectId: projectId,
                                                            taskId: taskId)
            }
        ) {
            Button("-") {
                Task {
                    await viewModel.deleteDependency(viewModel.taskInfo.taskDependsOn.id)
                    await viewModel.getTaskInfo(taskId)
                }
            }

            ForEach(viewModel.taskListForDependency, id: \.id) { task in
                Button(task.name ?? "-") {
                    Task {
                        await viewModel.addDependency(taskDependent: taskId,
                                                      taskDependsOn: task.id)
                        await viewModel.getTaskInfo(taskId)
                    }
                }
            }
        }
        Divider()

        MenuRow(categoryTitle,
                viewModel.taskInfo.categoryId.description.decodeCategory(),
                action: { await viewModel.updateTypeOfActivityList() })
        {
            Text("-")

            ForEach(viewModel.typeOfActivityList, id: \.id) { activity in
                Button(activity.name) {
                    Task {
                        let taskDto = TaskDTO()
                        taskDto.typeofactivityid = .init(value: activity.id)

                        await viewModel.updateTask(taskId, taskDTO: taskDto)
                        await viewModel.getTaskInfo(taskId)
                    }
                }
            }
        }
        Divider()

        MenuRow(taskStatusTitle,
                viewModel.taskInfo.statusId.description.decodeTaskStatus(),
                action: { await viewModel.updateStatusList() })
        {
            Text("-")

            ForEach(viewModel.statusList, id: \.id) { status in
                Button(status.name) {
                    Task {
                        let taskDto = TaskDTO()
                        taskDto.status = .init(value: status.id)

                        await viewModel.updateTask(taskId, taskDTO: taskDto)
                        await viewModel.getTaskInfo(taskId)
                    }
                }
            }
        }
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

    @ViewBuilder private let content: () -> Content
    private let action: () async -> Void

    init(_ leadingValue: String,
         _ trailingValue: String,

         action: @escaping () async -> Void,
         @ViewBuilder content: @escaping () -> Content) {
        self.leadingValue = leadingValue
        self.trailingValue = trailingValue

        self.action = action
        self.content = content
    }

    @ViewBuilder
    var body: some View {
        Menu {
            content().task { await action() }
        } label: {
            HStack {
                Text(leadingValue)

                Spacer()

                Text(trailingValue)
            }.foregroundColor(.primary)
        }
    }
}
