//
//  ProjectInfo.swift
//  TaskMaster
//
//  Created by  user on 16-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct ProjectInfo: TaskInfoProtocol {
    let id: UInt8
    let title: String
    let timerValue: UInt8

    let participiantsValue: UInt?
}
