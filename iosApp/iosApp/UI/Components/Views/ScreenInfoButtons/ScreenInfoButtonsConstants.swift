//
//  ScreenInfoButtonsConstants.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import CoreGraphics

enum ScreenInfoButtonsConstants {
    //    MARK: - Strings
    enum Strings {
        //    MARK: Titles
        enum Titles {
            static let estimatesTitle = "Рассчет времени"
            static let attachmentsTitle = "Вложения"
        }

        //    MARK: Image Names
        enum ImageNames {
            static let infoImageName = "arrow.right.circle"
        }
    }

    //    MARK: - Numbers
    enum Numbers {
        static let externalPadding: CGFloat = 64
        static let lineLimit: Int = 1
        static let minimumScaleFactor: CGFloat = 0.7
    }
}
