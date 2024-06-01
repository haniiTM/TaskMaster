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
    private let title: String
    private let action: () -> Void

    init(_ title: String,
         stateManager: ProjectListStateManager,
         viewModel: ProjectListViewModel,
         action: @escaping () -> Void) {
        self.title = title
        self.stateManager = stateManager
        self.viewModel = viewModel
        self.action = action
    }

    init(_ stateManager: ProjectListStateManager,
         viewModel: ProjectListViewModel) {
        self.stateManager = stateManager
        self.viewModel = viewModel
        title = "Удалить"
        action = {}
    }

    var body: some View {
        UserListAlertTemplate(
            title,
            viewModel: viewModel,
            action: {
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
