//
//  NavBar.swift
//  TaskMaster
//
//  Created by  user on 19-05-2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct NavBarTitle: View {
    private let title: String

    init(_ title: String) {
        self.title = title
    }

    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 8)
                .stroke(.tint)
                .frame(height: 50)

            Text(title)
                .font(.largeTitle)
        }.padding(.horizontal)
    }
}

struct NavBarView<Content: View>: View {
    @ViewBuilder private let content: () -> Content

    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    var body: some View {
        ZStack {
            content()
                .navigationBarTitleDisplayMode(.inline)
                .navigationBarBackButtonHidden()
                .toolbar {
                    ToolbarItem(placement: .principal) {
                        NavBarTitle("aboba")
                    }
                }
        }
    }
}
