//
//  TaskCardController.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

final class TaskCardController: TaskCardControllerProtocol {
    //    MARK: Props
    let model: TaskInfo

    //    MARK: Init
    required init(model: TaskInfo) {
        self.model = model
    }

    //    MARK: Methods
    func open() {}

    func remove() {}
}