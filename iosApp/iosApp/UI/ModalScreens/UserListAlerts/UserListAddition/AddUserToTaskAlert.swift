//
//  AddUserToTaskAlert.swift
//  TaskMaster
//
//  Created by  user on 04-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct AddUserToTaskAlert: View {
    @ObservedObject private var viewModel: SubTaskListViewModel
    @State private var userIdList = [KotlinInt]()
    private let stateManager: SubTaskListStateManager
    private let taskId: UInt16

    init(_ taskId: UInt16,
         stateManager: SubTaskListStateManager,
         viewModel: SubTaskListViewModel) {
        self.taskId = taskId
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        UserListAdditionAlert(
            //            viewModel.freeFromProjectUserListSignal,
            [PersonDTO(id: 0, surname: "abobich", name: "abob", patronymic: "abobov", role: "Тестирование")],
            userIdList: $userIdList,
            onAppear: onAppear,
            onConfirm: {
                Task { await onConfirm() }
            }
        )
    }

    private func onAppear() async {
        //        await viewModel.updateFreeFromProjectUserList(parentId)
    }

    private func onConfirm() async {
        //        await viewModel.linkUserListToProject(parentId, userIdList: userIdList)
        stateManager.isUserAdditionAlertVisible.toggle()
    }
}
