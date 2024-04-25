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
        ZStack {
            //            Image("moon-background").resizable()

            LinearGradient(gradient: Gradient(stops: [.init(color: .blue, location: GradientBGConstants.Numbers.startPoint),
                                                      .init(color: .black, location: GradientBGConstants.Numbers.endPoint)]),
                           startPoint: .top,
                           endPoint: .bottom)
            .aspectRatio(contentMode: .fill)
            .frame(minWidth: 0, maxWidth: .infinity)
            .edgesIgnoringSafeArea([.horizontal, .bottom])

            //            VStack(alignment: .leading) {
            //                Spacer()
            //
            //                Text("Space Travel")
            //                    .font(.largeTitle)
            //                    .fontWeight(.light)
            //
            //                Divider().background(Color.white).padding(.trailing, 128)
            //
            //                Text("Finally, it's here: Travelling to space. With just a few simple clicks, you can book your ticket on the next shuttle to the Moon!\n\nFor real adventurous travellers, we also offer trips to Mars. In our new shuttle X1, you will be there in no time with the newest and most comfortable travelling options.")
            //                    .fontWeight(.light)
            //
            //                Divider().background(Color.white).padding(.trailing, 128)
            //            }
            //            .foregroundColor(.white)
            //            .padding(.horizontal, 24)
            //            .padding(.bottom, 64)
        }
    }
}
