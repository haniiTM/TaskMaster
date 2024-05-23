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
        let reactNative = TaskInfo(title: "Изучение React Native",
                                   timerValue: 24,
                                   participiantsValue: 3)

        let graphQL = TaskInfo(title: "Изучение GraphQL",
                               timerValue: 17,
                               participiantsValue: 3)

        let tailwind = TaskInfo(title: "Изучение Tailwind",
                                timerValue: 13,
                                participiantsValue: 3)

        let dom = TaskInfo(title: "Изучение DOM",
                           timerValue: 9,
                           participiantsValue: 3)

        let ajax = TaskInfo(title: "Изучение Ajax",
                            timerValue: 18,
                            participiantsValue: 3)

        let localUncompletedTaskList = [reactNative, graphQL, tailwind]
        let localCompletedTaskList = [dom, ajax]

        localUncompletedTaskList.enumerated().forEach { index, task in
            let task = TaskInfo(title: task.title,
                                timerValue: task.timerValue,
                                participiantsValue: 3)

            privateUncompletedTaskList.append(task)
        }

        localCompletedTaskList.enumerated().forEach { index, task in
            let task = TaskInfo(title: task.title,
                                timerValue: task.timerValue,
                                participiantsValue: 3)

            privateCompletedTaskList.append(task)
        }
    }

    mutating func addUncompletedTask() {
        let newTask = TaskInfo(title: "Aboba",
                               timerValue: 0,
                               participiantsValue: 3)
        privateUncompletedTaskList.append(newTask)
    }

    mutating func addCompletedTask() {
        let firstRemoved = privateUncompletedTaskList.removeFirst()
        privateCompletedTaskList.append(firstRemoved)
    }
}
