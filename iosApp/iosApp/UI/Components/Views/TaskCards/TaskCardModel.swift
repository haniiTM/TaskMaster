//
//  TaskCardModel.swift
//  iosApp
//
//  Created by evilgen on 16.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct TaskCardModel {
    private var privateTaskList: [TaskInfo] = .init()

    var taskList: [TaskInfo] {
        privateTaskList
    }
}
