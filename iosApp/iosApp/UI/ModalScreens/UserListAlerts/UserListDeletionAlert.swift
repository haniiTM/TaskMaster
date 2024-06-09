//
//  UserListDeletionAlert.swift
//  TaskMaster
//
//  Created by  user on 29-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserListDeletionAlert: View {
    @ObservedObject private var viewModel: ProjectListViewModel
    @State private var userIdList = [KotlinInt]()
    private let stateManager: ProjectListStateManager

    init(_ stateManager: ProjectListStateManager,
         viewModel: ProjectListViewModel) {
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        UserListAlertTemplate(
            "Удалить",
            userList: viewModel.userListSignal,
            onAppear: viewModel.updateUserList,
            onConfirm: {
                Task { await deleteUser() }
            }
        ) { user in
            CheckableUserCard(userIdList: $userIdList, user: user)
        }
    }

    private func deleteUser() async {
        await viewModel.deleteUser(userIdList)
        stateManager.deleteUserState.toggle()
    }
}
