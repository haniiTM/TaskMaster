//
//  CustomTextField.swift
//  TaskMaster
//
//  Created by  user on 18-06-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct CustomTextField: View {
    private var title: String
    @Binding private var text: String

    init(_ title: String,
         _ text: Binding<String>) {
        self.title = title
        self._text = text
    }

    var body: some View {
        HStack {
            TextField(text: $text) {
                Text(title)
                    .padding(8)
            }

            Image(systemName: text.isEmpty ? "exclamationmark.triangle" : "")
                .foregroundColor(.pink)
        }
    }
}
