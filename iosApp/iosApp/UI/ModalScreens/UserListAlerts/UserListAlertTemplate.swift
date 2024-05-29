//
//  UserListAlertTemplate.swift
//  TaskMaster
//
//  Created by  user on 29-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct UserListAlertTemplate<Content: View>: View {
    private let list = ["Ivan Ivanov", "Ilia Ilich", "Roma Romavich"]

    private let title: String
    private let action: () -> Void
    private var content: (String) -> Content

    init(_ title: String,
         action: @escaping () -> Void,
         content: @escaping (String) -> Content) {
        self.title = title
        self.action = action
        self.content = content
    }

    var body: some View {
        VStack(spacing: 0) {
            List(list, id: \.self) { item in
                content(item)
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
