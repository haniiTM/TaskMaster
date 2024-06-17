//
//  NotificationAlert.swift
//  TaskMaster
//
//  Created by  user on 17-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct NotificationAlert {
    private let notificationList: [NotificationItem]
    private let title: String
    private let message: String

    init(_ notificationList: [NotificationItem]) {
        self.notificationList = notificationList
        title = "Уведомление"

        let messageTitle = "На вас была назначена задача."
        let messageSubTitle = "Пожалуйста, укажите, сколько времени вы готовы потратить ежедневно на задачу. Примите требуемое действие в задачах:"

        var taskTitleList = [String]()
        notificationList.forEach { item in
            guard
                let taskList = item.listTask as? [NotificationTask]
            else { return }

            taskList.forEach { task in
                guard
                    let title = task.taskName
                else { return }

                taskTitleList.append(title)
            }
        }

        message =
        messageTitle +
        "\n" +
        "\n" +
        messageSubTitle +
        "\n" +
        "\n" +
        taskTitleList.flatMap { title in
            title + "\n"
        }
    }

    var body: Alert {
        .init(
            title: Text(title),
            message: Text(message)
        )
    }
}
