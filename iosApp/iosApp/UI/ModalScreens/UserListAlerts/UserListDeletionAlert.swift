//
//  UserListDeletionAlert.swift
//  TaskMaster
//
//  Created by  user on 29-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct UserListDeletionAlert: View {
    private let action: () -> Void
    private let stateManager: ProjectListStateManager
    private let viewModel: ProjectListViewModel

    init(_ stateManager: ProjectListStateManager,
         viewModel: ProjectListViewModel,
         action: @escaping () -> Void) {
        self.stateManager = stateManager
        self.viewModel = viewModel
        self.action = action
    }

    init(_ stateManager: ProjectListStateManager,
         viewModel: ProjectListViewModel) {
        self.stateManager = stateManager
        self.viewModel = viewModel

        action = {
            stateManager.deleteUserState.toggle()
        }
    }

    var body: some View {
        UserListAlertTemplate("Delete",
                              viewModel: viewModel,
                              action: action) { user in
            HStack {
                Button(action: {}, label: {
                    Image(systemName: "square")
                })

                Text("\(user.surname) \(user.name)")

                Spacer()

                Image(systemName: "crown")
            }
        }
    }
}
