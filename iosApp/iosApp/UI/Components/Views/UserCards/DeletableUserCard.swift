//
//  DeletableUserCard.swift
//  TaskMaster
//
//  Created by  user on 01-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct DeletableUserCard<ViewModel: UserDeletable & TaskUserListUpdater>: View {
    @EnvironmentObject var userRoleManager: UserRoleManager
    @ObservedObject private var viewModel: ViewModel
    private let user: PersonDTO
    private let projectId: UInt16

    init(_ user: PersonDTO,
         projectId: UInt16,
         viewModel: ViewModel) {
        self.user = user
        self.projectId = projectId
        self.viewModel = viewModel
    }

    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        HStack {
            Image(systemName: user.role?.getIconByRole() ?? "exclamationmark.triangle")

            Text("\(user.surname) \(user.name)")

            Spacer()

            if userRoleManager.isAdmin {
                Menu {
                    Button(role: .destructive, action: {
                        Task {
                            await deleteUser()
                        }
                    }, label: {
                        Label("Удалить", systemImage: "trash")
                    })
                } label: {
                    Image(systemName: "ellipsis")
                }
            }
        }
    }

    private func deleteUser() async {
        let uInt16Id = user.id as? UInt16 ?? 0
        await viewModel.deleteUser(uInt16Id, parentId: projectId)
        await viewModel.updateUserList(projectId)
    }
}
