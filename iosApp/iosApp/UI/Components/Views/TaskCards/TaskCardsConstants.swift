//
//  TaskCardsConstants.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import CoreGraphics

enum TaskCardsConstants {
    //    MARK:  - Strings
    enum Strings {
        //        MARK: Titles
        enum Titles {
            static let numberTitle = "Задача №"
            static let timerTitle: (UInt16) -> String = { hours in
                "Время на выполнение: \(hours)"
            }
            static let participiantsTitle = "Участники: "
        }

        //        MARK: Empty Titles
        enum EmptyTitles {
            static let emptyNumberTitle = "0"
            static let emptyStringTitle = "Нет"
            static let emptyCategoriesTitle = "Нет тегов"
        }

        //        MARK: Separators
        enum Separators {
            static let numbersSeparator = "."
            static let categoriesSeparator = ", "
        }

        //        MARK: Image Names
        enum ImageNames {
            static let urgentImageName = "exclamationmark.triangle"
            static let removeIcon = "xmark.bin"
        }
    }

    //    MARK: - Numbers
    enum Numbers {
        static let lineSpacing: CGFloat = 8
    }
}

// MARK: Fonts
// title3 - 18
// body - 14
// subheadline - 12
// footnote - 10
