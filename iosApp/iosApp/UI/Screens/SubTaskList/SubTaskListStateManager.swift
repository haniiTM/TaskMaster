//
//  SubTaskListStateManager.swift
//  TaskMaster
//
//  Created by  user on 23-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

final class SubTaskListStateManager: TaskListStateManagerProtocol {
    @Published var isCreationAlertShown = false
    @Published var isUserListVisible = false
    @Published var isUserAdditionAlertVisible = false
    @Published var isCardDeletionAlertPresented = false
}
