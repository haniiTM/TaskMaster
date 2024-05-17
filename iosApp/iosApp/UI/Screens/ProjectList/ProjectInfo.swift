//
//  ProjectInfo.swift
//  TaskMaster
//
//  Created by  user on 16-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

class ProjectInfo: TaskInfoProtocol {
    var id: UInt8
    let title: String
    let timerValue: UInt8
    var participiantsValue: UInt8

    init(title: String, timerValue: UInt8, participiantsValue: UInt8) {
        id = .random(in: 1...99)
        self.title = title
        self.timerValue = timerValue
        self.participiantsValue = participiantsValue
    }

    init(_ dto: TaskDTO) {
        id = dto.id as? UInt8 ?? 0
        title = dto.name ?? "Нет названия"
        timerValue = dto.scope as? UInt8 ?? 0
        participiantsValue = dto.userCount as? UInt8 ?? 0
    }
}
