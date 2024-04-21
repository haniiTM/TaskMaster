//
//  TaskCardsConstants.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import CoreGraphics

enum TaskCardsConstants {
    //    MARK: Strings
    enum Strings {
        static let numberTitle = "Задача №"
        static let timerTitle: (UInt8) -> String = { hours in
            "Время на выполнение: \(hours) часа"
        }
        static let categoriesSeparator = ", "
        static let participiantsTitle = "Участники: "
    }

    //    MARK: ImageStrings
    enum ImageStrings {
        static let urgentImageName = "exclamationmark.triangle"
        static let removeIcon = "xmark.bin"
    }

    //    MARK: Numbers
    enum Numbers {
        static let lineSpacing: CGFloat = 8
    }
}

// MARK: Fonts
// title3 - 18
// body - 14
// subheadline - 12
// footnote - 10
