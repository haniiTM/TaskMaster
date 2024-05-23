//
//  ProjectInfo.swift
//  TaskMaster
//
//  Created by  user on 16-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import shared

class ProjectInfo: TaskInfoProtocol {
    var id: UInt16 = 0
    var title: String = "Нет названия"
    var timerValue: UInt16 = 0
    var participiantsValue: UInt8 = 0

    init(id: UInt16 = .random(in: 1...99), title: String, timerValue: UInt16, participiantsValue: UInt8) {
        self.id = id
        self.title = title
        self.timerValue = timerValue
        self.participiantsValue = participiantsValue
    }

    init(_ dto: TaskDTO) {
        id = dto.id as? UInt16 ?? self.id
        title = dto.name ?? self.title
        timerValue = dto.scope as? UInt16 ?? self.timerValue
        participiantsValue = dto.userCount as? UInt8 ?? self.participiantsValue
    }

    init() {}
}
