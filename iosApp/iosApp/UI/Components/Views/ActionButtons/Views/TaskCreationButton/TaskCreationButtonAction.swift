//
//  TaskCreationButtonAction.swift
//  iosApp
//
//  Created by evilgen on 19.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct TaskCreationButtonAction: Openable {
    private let stateManager: TaskListStateManager

    init(stateManager: TaskListStateManager) {
        self.stateManager = stateManager
    }

    func open() { stateManager.addTaskState.toggle() }
}
