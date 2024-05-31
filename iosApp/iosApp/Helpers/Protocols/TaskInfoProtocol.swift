//
//  TaskInfoProtocol.swift
//  TaskMaster
//
//  Created by  user on 17-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

protocol TaskInfoProtocol: ProjectInfoProtocol {
    var description: String { get }
    var statusId: UInt8 { get }
}
