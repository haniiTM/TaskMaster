//
//  GradientBG.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct GradientBG: View {
    @Environment(\.colorScheme) private var colorScheme

    var body: some View {
        ZStack {
            LinearGradient(gradient: .init(stops: [.init(color: colorScheme == .dark ? .ultramarine : .white,
                                                         location: GradientBGConstants.Numbers.startPoint),
                                                   .init(color: colorScheme == .dark ? .black : .deepBlue,
                                                         location: GradientBGConstants.Numbers.endPoint)]),
                           startPoint: .top,
                           endPoint: .bottom)
            .aspectRatio(contentMode: .fill)
            .frame(minWidth: 0, maxWidth: .infinity)
            .edgesIgnoringSafeArea(.all)
        }
    }
}
