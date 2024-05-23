//
//  StartView.swift
//  TaskMaster
//
//  Created by  user on 23-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct StartView: View {
    @EnvironmentObject var authViewModel: AuthViewModel

    var body: some View {
        if authViewModel.isAuthenticated {
            ProjectListView()
        } else {
            LoginScreenView()
        }
    }
}
