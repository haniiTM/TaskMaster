//
//  TaskInfoStateManager.swift
//  TaskMaster
//
//  Created by  user on 27-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

final class TaskInfoStateManager: UserListVisible, UserAdditionAlertVisible {
    @Published var isCreationAlertShown = false
    @Published var isUserListVisible = false
    @Published var isUserAdditionAlertVisible = false
    @Published var isTimeEditAlertVisible = false
}
