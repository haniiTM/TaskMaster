//
//  GradientBG.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct GradientBG: View {
    var body: some View {
        LinearGradient(gradient: Gradient(stops: [.init(color: .blue, location: GradientBGConstants.Numbers.startPoint),
                                                  .init(color: .black, location: GradientBGConstants.Numbers.endPoint)]),
                       startPoint: .top,
                       endPoint: .bottom)
        .ignoresSafeArea(edges: [.horizontal, .bottom])
    }
}
