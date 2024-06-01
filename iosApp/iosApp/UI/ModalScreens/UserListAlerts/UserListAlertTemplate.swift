//
//  UserListAlertTemplate.swift
//  TaskMaster
//
//  Created by  user on 29-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserListAlertTemplate<Content: View, T: UserListable>: View {
    @ObservedObject private var viewModel: T

    private let title: String
    private let onAppear: () async -> Void
    private let onConfirm: () -> Void
    private var content: (PersonDTO) -> Content

    init(_ title: String,
         viewModel: T,
         onAppear: @escaping () async -> Void,
         onConfirm: @escaping () -> Void,
         content: @escaping (PersonDTO) -> Content) {
        self.title = title
        self.viewModel = viewModel
        self.onAppear = onAppear
        self.onConfirm = onConfirm
        self.content = content
    }

    var body: some View {
        ViewBody
            .task { await onAppear() }
    }

    private var ViewBody: some View {
        VStack(spacing: 0) {
            List(viewModel.userListSignal, id: \.id) { user in
                content(user)
            }
            .listStyle(.grouped)
            .frame(height: 165)

            Button(action: onConfirm, label: {
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
