//
//  SubTaskListModel.swift
//  TaskMaster
//
//  Created by evilgen on 22.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct SubTaskListModel {
    //    MARK: Props
    var subTaskList: [TaskInfo] { privateSubTaskList }
    private var privateSubTaskList: [TaskInfo] = .init()

    //    MARK: Init
    init() {
        setupDefaultSubTaskList()
    }

    //    MARK: Methods
    private mutating func setupDefaultSubTaskList() {
        let reactNativeSyntax = TaskInfo(title: "Изучение синтаксиса",
                                         timerValue: 8,
                                         isUrgent: true,
                                         categories: ["Frontend"])

        let reactNativeUI = TaskInfo(title: "Изучение графических элементов React Native",
                                     timerValue: 6,
                                     isUrgent: false,
                                     categories: ["Frontend", "Mobile"])

        let redux = TaskInfo(title: "Изучение Redux",
                             timerValue: 10,
                             isUrgent: false,
                             categories: ["Mobile"])

        let localSubTaskList = [reactNativeSyntax, reactNativeUI, redux]

        localSubTaskList.enumerated().forEach { index, task in
            let task = TaskInfo(parentNumber: 1,
                                numberValue: UInt8(index) + 1,
                                title: task.title,
                                timerValue: task.timerValue,
                                isUrgent: task.isUrgent,
                                categories: task.categories)

            privateSubTaskList.append(task)
        }
    }
}
