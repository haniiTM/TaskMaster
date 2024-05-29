//
//  UserCard.swift
//  TaskMaster
//
//  Created by  user on 29-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct UserCard: View {
    @State var isChecked = false
    @Binding var userIdList: [KotlinInt]
    let user: PersonDTO

    var body: some View {
        HStack {
            Button(
                action: {
                    isChecked.toggle()

                    isChecked
                    ? userIdList.append(user.id ?? 0)
                    : userIdList.removeAll(where: { userId in
                        self.user.id == userId
                    })
                },

                label: {
                    Image(systemName: isChecked ? "checkmark.square" : "square")
                }
            )

            Text("\(user.surname) \(user.name)")

            Spacer()

            Image(systemName: user.role?.getIconByRole() ?? "exclamationmark.triangle")
        }
    }
}
