//
//  UserListAlertTemplate.swift
//  TaskMaster
//
//  Created by  user on 29-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserListAlertTemplate<Content: View, T: ProjectListViewModelProtocol>: View {
    @ObservedObject private var viewModel: T

    private let title: String
    private let action: () -> Void
    private var content: (PersonDTO) -> Content

    init(_ title: String,
         viewModel: T,
         action: @escaping () -> Void,
         content: @escaping (PersonDTO) -> Content) {
        self.title = title
        self.viewModel = viewModel
        self.action = action
        self.content = content
    }

    var body: some View {
        ViewBody
            .task {
                await viewModel.updateUserList()
            }
    }

    private var ViewBody: some View {
        VStack(spacing: 0) {
            List(viewModel.userListSignal, id: \.id) { user in
                content(user)
            }
            .listStyle(.grouped)
            .frame(height: 165)

            Button(action: action, label: {
                Text(title)
                    .frame(maxWidth: .infinity)
                    .padding(.vertical, 8)
                    .background(Color(uiColor: .secondarySystemBackground))
                    .border(Color.primary.opacity(0.1))
            })
        }
        .padding(.horizontal)
    }
}
