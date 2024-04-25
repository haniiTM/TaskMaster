//
//  Logo.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

enum AppIconProvider {
    static func appIcon(in bundle: Bundle = .main) -> String {
        guard let icons = bundle.object(forInfoDictionaryKey: "CFBundleIcons") as? [String: Any],
              let primaryIcon = icons["CFBundlePrimaryIcon"] as? [String: Any],
              let iconFiles = primaryIcon["CFBundleIconFiles"] as? [String],
              let iconFileName = iconFiles.last else {
            fatalError("Could not find icons in bundle")
        }

        return iconFileName
    }
}

struct Logo: View {
    let appIcon: String

    var body: some View {
        HStack(alignment: .center) {
            if let image = UIImage(named: appIcon) {
                Image(uiImage: image)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
            }

            Spacer()

            VStack() {
                Text("Welcome to the")
                    .font(.title3)

                Text("SÉBBIA")
                    .font(.largeTitle)
            }
//            .padding()
//            .padding()
        }
        .fixedSize()
        .accessibilityElement(children: .ignore)
    }
}
