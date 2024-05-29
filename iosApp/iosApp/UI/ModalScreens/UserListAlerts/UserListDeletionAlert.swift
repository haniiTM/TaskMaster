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

    init(_ stateManager: ProjectListStateManager, action: @escaping () -> Void) {
        self.stateManager = stateManager
        self.action = action
    }

    init(_ stateManager: ProjectListStateManager) {
        self.stateManager = stateManager

        action = {
            stateManager.deleteUserState.toggle()
        }
    }

    var body: some View {
        UserListAlertTemplate("Delete", action: action) { item in
            HStack {
                Button(action: {}, label: {
                    Image(systemName: "square")
                })

                Text(item)

                Spacer()

                Image(systemName: "crown")
            }
        }
    }
}
