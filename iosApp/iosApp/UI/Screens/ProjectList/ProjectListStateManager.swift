//
//  ProjectListStateManager.swift
//  TaskMaster
//
//  Created by  user on 21-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

final class ProjectListStateManager: CardDeletionAlertPresentable {
    @Published var addProjectState = false
    @Published var addUserState = false
    @Published var deleteUserState = false

    @Published var isLogOutAlertPresented = false
    @Published var isCardDeletionAlertPresented = false
}
