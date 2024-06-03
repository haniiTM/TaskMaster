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

    init(_ stateManager: TaskListStateManager,
         viewModel: TaskListViewModel) {
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        UserListAlertTemplate(
            "Добавить",
            userList: [.init(id: 0, surname: "Abobich", name: "Abob", patronymic: nil, role: "Тестирование")],
            onAppear: {},
            onConfirm: {
                //                Task { await deleteUser() }
                addUserList()
            }
        ) { user in
            CheckableUserCard(userIdList: $userIdList, user: user)
        }
    }

    private func addUserList() /*async*/ {
        //        await viewModel.deleteUser(userIdList)
        stateManager.isAddUserToProjectAlertVisible.toggle()
    }
}
