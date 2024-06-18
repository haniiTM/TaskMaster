//
//  UserCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 28-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserCreationAlert: View {
    @ObservedObject private var viewModel: ProjectListViewModel
    @ObservedObject private var stateManager: ProjectListStateManager

    @State private var isEmpty = true

    @State private var lastName = ""
    @State private var firstName = ""

    @State private var login = ""
    @State private var password = ""

    @State private var roleTitle = "Выбор роли"
    @State private var roleId: UInt8 = 0

    init(_ stateManager: ProjectListStateManager, viewModel: ProjectListViewModel) {
        self.stateManager = stateManager
        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Добавить пользователя",
                              "Добавить",
                              $isEmpty)
        { ViewBody } action: {
            Task { await createUser() }
        }
    }

    private var ViewBody: some View {
        UserCreationForm
            .task { await viewModel.updateUserRoleList() }
            .onChange(of: lastName) { _ in checkIfEmpty() }
            .onChange(of: firstName) { _ in checkIfEmpty() }
            .onChange(of: login) { _ in checkIfEmpty() }
            .onChange(of: password) { _ in checkIfEmpty() }
            .onChange(of: roleTitle) { _ in checkIfEmpty() }
    }

    @ViewBuilder
    private var UserCreationForm: some View {
        CustomTextField("Фамилия",
                        $lastName)

        CustomTextField("Имя",
                        $firstName)

        CustomTextField("Логин",
                        $login)

        CustomTextField("Пароль",
                        $password)

        Menu {
            ForEach(viewModel.userRoleListSignal.reversed(), id: \.id) { role in
                Button {
                    roleId = UInt8(role.id)
                    roleTitle = role.name
                } label: {
                    Label {
                        Text(role.name)
                    } icon: {
                        Image(systemName: role.name.getIconByRole())
                    }
                }
            }
        } label: {
            HStack {
                Text(roleTitle)

                Spacer()

                if roleTitle == "Выбор роли" {
                    Image(systemName: "exclamationmark.triangle")
                        .tint(.pink)
                }

                Image(systemName: "arrow.uturn.down.circle").scaleEffect(x: -1)
            }
        }.tint(.primary)
    }

    private func createUser() async {
        let user = RegisterReceiveRemote(login: login,
                                         password: password,
                                         fio: lastName + " " + firstName,
                                         role: roleTitle)

        await viewModel.createUser(user)
        stateManager.addUserState.toggle()
    }

    private func checkIfEmpty() {
        isEmpty = lastName.isEmpty || firstName.isEmpty || login.isEmpty || password.isEmpty || roleTitle == "Выбор роли"
    }
}
