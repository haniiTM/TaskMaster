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
    private let projectId: UInt16

    init(_ projectId: UInt16,
         stateManager: TaskListStateManager,
         viewModel: TaskListViewModel) {
        self.projectId = projectId
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        UserListAlertTemplate(
            "Добавить пользователя",
            userList: viewModel.userListSignal,
            onAppear: onAppear,
            onConfirm: onConfirm
        ) { user in
            DeletableUserCard(user, projectId: projectId, viewModel: viewModel)
        }
    }

    private func onAppear() async {
        await viewModel.updateUserList(projectId)
    }

    private func onConfirm() {
        stateManager.isUserListVisible.toggle()
        stateManager.isAddUserToProjectAlertVisible.toggle()
    }
}
