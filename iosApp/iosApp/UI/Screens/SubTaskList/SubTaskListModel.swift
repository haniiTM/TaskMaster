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
                                         participiantsValue: 3)

        let reactNativeUI = TaskInfo(title: "Изучение графических элементов React Native",
                                     timerValue: 6,
                                     participiantsValue: 3)

        let redux = TaskInfo(title: "Изучение Redux",
                             timerValue: 10,
                             participiantsValue: 3)

        let localSubTaskList = [reactNativeSyntax, reactNativeUI, redux]

        localSubTaskList.enumerated().forEach { index, task in
            let task = TaskInfo(title: task.title,
                                timerValue: task.timerValue,
                                participiantsValue: 3)

            privateSubTaskList.append(task)
        }
    }
}
