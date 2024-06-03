//
//  TaskListStateManager.swift
//  TaskMaster
//
//  Created by  user on 22-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

protocol TaskListStateManagerProtocol: ObservableObject {
    var isUserListVisible: Bool { get set }
}

final class TaskListStateManager: TaskListStateManagerProtocol {
    @Published var addTaskState = false
    @Published var isUserListVisible = false
    @Published var isAddUserToProjectAlertVisible = false
}
