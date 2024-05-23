//
//  SubTaskCreationButtonAction.swift
//  TaskMaster
//
//  Created by evilgen on 22.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

struct SubTaskCreationButtonAction: Openable {
    private let stateManager: SubTaskListStateManager

    init(stateManager: SubTaskListStateManager) {
        self.stateManager = stateManager
    }

    func open() { stateManager.isCreationAlertShown.toggle() }
}
