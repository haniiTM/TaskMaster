//
//  TaskInfo.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

class TaskInfo: ProjectInfo, TaskInfoProtocol {
    var description: String
    var statusId: UInt8

    override init(_ dto: TaskDTO) {
        description = dto.content ?? "Нет описания"
        statusId = dto.status as? UInt8 ?? 0
        super.init(dto)
    }
    
    override init(id: UInt16 = .random(in: 1...99), title: String, timerValue: UInt16, participiantsValue: UInt8) {
        description = "Emtpy desc"
        statusId = 0
        super.init(title: title, timerValue: timerValue, participiantsValue: participiantsValue)
    }
}
