//
//  TaskListModel.swift
//  TaskMaster
//
//  Created by evilgen on 21.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct TaskListModel {
    //    MARK: Props
    var uncompletedTaskList: [TaskInfo] { privateUncompletedTaskList }
    var completedTaskList: [TaskInfo] { privateCompletedTaskList }

    private var privateUncompletedTaskList: [TaskInfo] = .init()
    private var privateCompletedTaskList: [TaskInfo] = .init()

    //    MARK: Init
    init() {
        setupDefaultTaskList()
    }

    //    MARK: Methods
    private mutating func setupDefaultTaskList() {
        var reactNative = TaskInfo(title: "Изучение React Native",
                                   timerValue: 24,
                                   isUrgent: true,
                                   categories: ["Frontend", "Mobile"],
                                   isCompleted: false)

        var graphQL = TaskInfo(title: "Изучение GraphQL",
                               timerValue: 17,
                               isUrgent: true,
                               categories: ["Backend"],
                               isCompleted: false)

        var tailwind = TaskInfo(title: "Изучение Tailwind",
                                timerValue: 13,
                                isUrgent: false,
                                categories: ["Frontend"],
                                isCompleted: false)

        var dom = TaskInfo(title: "Изучение DOM",
                           timerValue: 9,
                           isUrgent: false,
                           categories: ["Frontend"],
                           isCompleted: true)

        var ajax = TaskInfo(title: "Изучение Ajax",
                            timerValue: 18,
                            isUrgent: false,
                            categories: ["Frontend"],
                            isCompleted: true)

        let localUncompletedTaskList = [reactNative, graphQL, tailwind]
        let localCompletedTaskList = [dom, ajax]

        localUncompletedTaskList.enumerated().forEach { index, task in
            let task = TaskInfo(numberValue: UInt8(index) + 1,
                                title: task.title,
                                timerValue: task.timerValue,
                                isUrgent: task.isUrgent,
                                categories: task.categories,
                                isCompleted: task.isCompleted)

            privateUncompletedTaskList.append(task)
        }

        localCompletedTaskList.enumerated().forEach { index, task in
            let task = TaskInfo(numberValue: UInt8(index) + 1,
                                title: task.title,
                                timerValue: task.timerValue,
                                isUrgent: task.isUrgent,
                                categories: task.categories,
                                isCompleted: task.isCompleted)

            privateCompletedTaskList.append(task)
        }
    }

    mutating func addUncompletedTask() {
        let newTask = TaskInfo(title: "Example Task",
                               timerValue: 0,
                               isUrgent: false,
                               categories: ["Example Categories"],
                               isCompleted: false)
        privateUncompletedTaskList.append(newTask)
    }

    mutating func addCompletedTask() {
        let firstRemoved = privateUncompletedTaskList.removeFirst()
        privateCompletedTaskList.append(firstRemoved)
    }
}
