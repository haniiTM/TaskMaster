//
//  TaskListStateManager.swift
//  TaskMaster
//
//  Created by  user on 22-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

final class TaskListStateManager: TaskListStateManagerProtocol {
    @Published var addTaskState = false
    @Published var isUserListVisible = false
    @Published var isUserAdditionAlertVisible = false
    @Published var isCardDeletionAlertPresented = false
    @Published var isPendingTaskAlertPresented = false
}
