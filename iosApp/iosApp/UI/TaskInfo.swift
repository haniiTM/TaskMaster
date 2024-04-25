//
//  TaskInfo.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

struct TaskInfo: Identifiable {
    let id = UUID()
    var parentNumber: UInt8?
    var numberValue: UInt8?
    let title: String
    let timerValue: UInt8
    let isUrgent: Bool
    var participiantsValue: UInt8?
    var categories: [String]?
    var isCompleted: Bool?
}
