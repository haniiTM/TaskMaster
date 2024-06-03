//
//  UserListAdditionAlert.swift
//  TaskMaster
//
//  Created by  user on 03-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserListAdditionAlert: View {
    @ObservedObject private var viewModel: TaskListViewModel
    @State private var userIdList = [KotlinInt]()
    private let stateManager: TaskListStateManager
    private let parentId: UInt16

    init(_ parentId: UInt16,
         stateManager: TaskListStateManager,
         viewModel: TaskListViewModel) {
        self.parentId = parentId
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        UserListAlertTemplate(
            "Добавить",
            userList: viewModel.freeFromProjectUserListSignal,
            onAppear: onAppear,
            onConfirm: {
                Task { await onConfirm() }
            }
        ) { user in
            CheckableUserCard(userIdList: $userIdList, user: user)
        }
    }

    private func onAppear() async {
        await viewModel.updateFreeFromProjectUserList(parentId)
    }

    private func onConfirm() async {
        await viewModel.linkUserListToProject(parentId, userIdList: userIdList)
        stateManager.isAddUserToProjectAlertVisible.toggle()
    }
}
