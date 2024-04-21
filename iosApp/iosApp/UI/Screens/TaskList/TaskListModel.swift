//
//  TaskListModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct TaskListModel {
    //    MARK: Props
    var taskList: [TaskInfo] { privateTaskList }
    private var privateTaskList: [TaskInfo] = .init()

    //    MARK: Init
    init() {
        setupDefaultTaskList()
    }

    //    MARK: Methods
    private mutating func setupDefaultTaskList() {
        var reactNative = TaskInfo(title: "Изучение React Native",
                                   timerValue: 24,
                                   isUrgent: true,
                                   categories: ["Frontend", "Mobile"])

        var graphQL = TaskInfo(title: "Изучение GraphQL",
                               timerValue: 17,
                               isUrgent: false,
                               categories: ["Backend"])

        var tailwind = TaskInfo(title: "Изучение Tailwind",
                                timerValue: 13,
                                isUrgent: false,
                                categories: ["Frontend"])

        let localTaskList = [reactNative, graphQL, tailwind]
        
        localTaskList.enumerated().forEach { index, task in
            let task = TaskInfo(numberValue: UInt8(index) + 1,
                                title: task.title,
                                timerValue: task.timerValue,
                                isUrgent: task.isUrgent,
                                categories: task.categories)

            privateTaskList.append(task)
        }
    }
}
