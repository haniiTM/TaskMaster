//
//  UserListAlert.swift
//  TaskMaster
//
//  Created by  user on 01-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct UserListAlert: View {
    @ObservedObject private var viewModel: TaskListViewModel
    private let stateManager: TaskListStateManager

    init(_ stateManager: TaskListStateManager,
         viewModel: TaskListViewModel) {
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        UserListAlertTemplate(
            "Добавить пользователя",
            viewModel: viewModel,
            action: {
                stateManager.isUserListVisible.toggle()
            }
        ) { user in
            DeletableUserCard(user)
        }
    }
}
