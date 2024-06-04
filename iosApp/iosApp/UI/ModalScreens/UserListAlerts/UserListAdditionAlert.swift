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
    @Binding private var userIdList: [KotlinInt]
    private let userList: [PersonDTO]
    private let onAppear: () async -> Void
    private let onConfirm: () -> Void

    init(_ userList: [PersonDTO],
         userIdList: Binding<[KotlinInt]>,
         onAppear: @escaping () async -> Void,
         onConfirm: @escaping () -> Void) {
        self.userList = userList
        self._userIdList = userIdList
        self.onAppear = onAppear
        self.onConfirm = onConfirm
    }

    var body: some View {
        UserListAlertTemplate(
            "Добавить",
            userList: userList,
            onAppear: onAppear,
            onConfirm: { onConfirm() }
        ) { user in
            CheckableUserCard(userIdList: $userIdList, user: user)
        }
    }
}
