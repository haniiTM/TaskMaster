//
//  ProjectInfoProtocol.swift
//  TaskMaster
//
//  Created by  user on 30-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol ProjectInfoProtocol: Identifiable {
    var id: UInt16 { get }
    var title: String { get }
    var timerValue: UInt16 { get }
    var participiantsValue: UInt8 { get }
}
