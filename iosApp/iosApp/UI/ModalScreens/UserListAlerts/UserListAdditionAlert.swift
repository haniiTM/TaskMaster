//
//  UserListAdditionAlert.swift
//  TaskMaster
//
//  Created by  user on 03-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserListAdditionAlert<ViewModel: FreeFromProjectUserListUpdater & UserAppendable,
                             StateManager: UserAdditionAlertVisible>: View {
    @ObservedObject private var viewModel: ViewModel
    private let stateManager: StateManager

    private let parentId: UInt16
    @State private var userIdList = [KotlinInt]()

    init(_ parentId: UInt16,
         stateManager: StateManager,
         viewModel: ViewModel) {
        self.viewModel = viewModel
        self.stateManager = stateManager
        self.parentId = parentId
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
        await viewModel.addUser(parentId, userIdList: userIdList)
        stateManager.isUserAdditionAlertVisible.toggle()
    }
}
