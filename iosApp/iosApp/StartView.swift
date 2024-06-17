//
//  StartView.swift
//  TaskMaster
//
//  Created by  user on 23-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct StartView: View {
    @EnvironmentObject var authManager: AuthManager

    var body: some View {
        if authManager.isAuthenticated {
            ProjectListView()
        } else {
            LoginScreenView()
        }
    }
}
