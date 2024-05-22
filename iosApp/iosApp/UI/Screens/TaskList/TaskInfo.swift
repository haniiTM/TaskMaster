//
//  TaskInfo.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

class TaskInfo: ProjectInfo {
    var description: String
    
    override init(_ dto: TaskDTO) {
        description = dto.content ?? "Нет описания"
        super.init(dto)
    }
    
    override init(id: UInt16 = .random(in: 1...99), title: String, timerValue: UInt8, participiantsValue: UInt8) {
        description = "Emtpy desc"
        super.init(title: title, timerValue: timerValue, participiantsValue: participiantsValue)
    }
}
