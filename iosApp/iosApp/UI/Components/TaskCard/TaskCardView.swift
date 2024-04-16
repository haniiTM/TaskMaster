//
//  TaskCardView.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct TaskCardView: View {
    //    MARK: - Props
    private let taskInfo: TaskInfo

    //    MARK: - Init
    init(taskInfo: TaskInfo) {
        self.taskInfo = taskInfo
    }

    var body: some View {
        TaskCardBody()
    }

    //    MARK: - Methods
    private func TaskCardBody() -> some View {
        HStack {
            VStack(alignment: .leading, spacing: getLineSpacing()) {
                if let unwrappedNumberValue = taskInfo.numberValue {
                    Text(getNumberTitle() + unwrappedNumberValue.description)
                }

                Text(taskInfo.title)

                HStack {
                    Text(getTimerTitle())

                    Spacer()

                    if taskInfo.isUrgent {
                        Image(systemName: getUrgentImageName())
                    }
                }

                if let unwrappedParticipiantsValue = taskInfo.participiantsValue {
                    Text(getParticipiantsTitle() + unwrappedParticipiantsValue.description)
                }

                if let unwrappedCategories = taskInfo.categories {
                    Text(unwrappedCategories.joined(separator: getCategoriesSeparator()))
                }
            }

            Spacer()
        }
        .padding()
        .border(.primary)
    }
}

//    MARK: - Constants
private extension TaskCardView {
    //    MARK: Strings
    func getNumberTitle() -> String { "Задача №" }
    func getTimerTitle() -> String { "Время на выполнение: \(taskInfo.timerValue) часа" }
    func getParticipiantsTitle() -> String { "Участники: " }
    func getUrgentImageName() -> String { "exclamationmark.triangle" }
    func getCategoriesSeparator() -> String { ", " }

    //    MARK: Numbers
    func getLineSpacing() -> CGFloat { 16 }
}
