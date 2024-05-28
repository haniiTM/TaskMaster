//
//  UserCreationAlert.swift
//  TaskMaster
//
//  Created by  user on 28-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct UserCreationAlert: View {
    //    @ObservedObject private var viewModel: ProjectListViewModel
    @ObservedObject private var stateManager: ProjectListStateManager

    @State private var lastName = ""
    @State private var firstName = ""
    @State private var login = ""
    @State private var password = ""
    @State private var roleTitle = "Роль"
    @State private var roleId: UInt8 = 0

    init(_ stateManager: ProjectListStateManager/*, viewModel: ProjectListViewModel*/) {
        self.stateManager = stateManager
        //        self.viewModel = viewModel
    }

    var body: some View {
        TemplateCreationAlert("Добавить пользователя")
        { ViewBody } action: {
            //            Task { await addProject(text) }
            addUser()
        }
    }

    @ViewBuilder
    private var ViewBody: some View {
        TextField(text: $lastName) {
            Text("Фамилия")
                .padding()
        }

        TextField(text: $firstName) {
            Text("Имя")
                .padding()
        }

        TextField(text: $login) {
            Text("Логин")
                .padding()
        }

        TextField(text: $password) {
            Text("Пароль")
                .padding()
        }

        Menu {
//            ForEach(viewModel.categoryListSignal, id: \.id) { category in
//            Button(category.name) {
//                    categoryId = UInt8(category.id)
//                    categoryMenuTitle = category.name
//                }
//            }
        } label: {
            HStack {
                Text(roleTitle)

                Spacer()

                Image(systemName: "arrow.uturn.down.circle").scaleEffect(x: -1)
            }
        }.tint(.primary)
    }

    private func addUser() /*async*/ {
        //        await viewModel.createProject(title)
        stateManager.addUserState.toggle()
    }
}
