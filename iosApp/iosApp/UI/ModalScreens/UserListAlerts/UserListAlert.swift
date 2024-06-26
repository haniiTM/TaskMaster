//
//  UserListAlert.swift
//  TaskMaster
//
//  Created by  user on 01-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserListAlert<ViewModel: UserListable & TaskUserListUpdater & UserDeletable,
                     StateManager: UserListVisible & UserAdditionAlertVisible>: View {
    @ObservedObject private var viewModel: ViewModel
    private let stateManager: StateManager
    private let parentId: UInt16

    init(_ parentId: UInt16,
         stateManager: StateManager,
         viewModel: ViewModel) {
        self.parentId = parentId
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
            DeletableUserCard(user, projectId: parentId, viewModel: viewModel)
        }
    }

    private func onAppear() async {
        await viewModel.updateUserList(parentId)
    }

    private func onConfirm() {
        stateManager.isUserListVisible.toggle()
        stateManager.isUserAdditionAlertVisible.toggle()
    }
}
