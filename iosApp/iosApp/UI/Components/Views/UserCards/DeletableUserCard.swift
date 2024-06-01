//
//  DeletableUserCard.swift
//  TaskMaster
//
//  Created by  user on 01-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct DeletableUserCard: View {
    //    @ObservedObject private var viewModel: TaskListViewModel
    private let user: PersonDTO

    init(_ user: PersonDTO/*, viewModel: TaskListViewModel*/) {
        self.user = user
        //        self.viewModel = viewModel
    }

    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        HStack {
            Image(systemName: user.role?.getIconByRole() ?? "exclamationmark.triangle")

            Text("\(user.surname) \(user.name)")

            Spacer()

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

    private func deleteUser() async {
        //        await viewModel.deleteUserFromProject()
    }
}
