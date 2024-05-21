//
//  DetailedTaskInfo.swift
//  TaskMaster
//
//  Created by  user on 18-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

class DetailedTaskInfo: ProjectInfo {
    var allocatedTime: UInt8 = 0
    var spentTime: String = "00:00"

    var taskDependsOn: TaskDependsOn = .init(nil)

    var categoryId: UInt8 = 0

    var statusId: UInt8 = 0

    //    let canAddManHours: String
    //    let projectId: String
    //    let triggerRefresh: String
    //    let taskIdDependenceOn: String
    //    let haveNotChild: String

    init(_ task: TaskByID) {
        let id = task.id as? UInt16 ?? 0
        let title = task.name ?? "Задача"
        let participiantsValue = task.userCount as? UInt8 ?? 0

        allocatedTime = task.spentTime as? UInt8 ?? self.allocatedTime
        let timerValue = task.score as? UInt8 ?? 0
        spentTime = task.spentedTime ?? self.spentTime

        let taskDependsOn = TaskDependsOn(task.taskDependenceOn)
        self.taskDependsOn = taskDependsOn

        categoryId = task.typeofactivityid as? UInt8 ?? self.categoryId

        statusId = task.status as? UInt8 ?? self.statusId

        super.init(id: id, title: title, timerValue: timerValue, participiantsValue: participiantsValue)
    }

    override init() {
        super.init()
    }
}

struct TaskDependsOn {
    var id: UInt8 = 0
    var name: String = "-"

    init(_ task: TaskDependenceOn?) {
        //        guard let task = task else { return  }

        //        if let unwrappedTask = task {
        id = task?.id as? UInt8 ?? self.id
        name = task?.name ?? self.name
        //        } else {
        //            id = 0
        //            name = "Родительская задача"
        //        }
    }
}
