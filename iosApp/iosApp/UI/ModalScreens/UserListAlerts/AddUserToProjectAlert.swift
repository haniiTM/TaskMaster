//
//  AddUserToProjectAlert.swift
//  TaskMaster
//
//  Created by  user on 04-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct AddUserToProjectAlert: View {
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
        UserListAdditionAlert(
            viewModel.freeFromProjectUserListSignal,
            userIdList: $userIdList,
            onAppear: onAppear,
            onConfirm: {
                Task { await onConfirm() }
            }
        )
    }

    private func onAppear() async {
        await viewModel.updateFreeFromProjectUserList(parentId)
    }

    private func onConfirm() async {
        await viewModel.linkUserListToProject(parentId, userIdList: userIdList)
        stateManager.isAddUserToProjectAlertVisible.toggle()
    }
}
