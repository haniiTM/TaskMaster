//
//  TaskInfoProtocol.swift
//  TaskMaster
//
//  Created by  user on 17-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol TaskInfoProtocol: Identifiable {
    var id: UInt8 { get }
    var title: String { get }
    var timerValue: UInt8 { get }
    var participiantsValue: UInt8 { get }
}
