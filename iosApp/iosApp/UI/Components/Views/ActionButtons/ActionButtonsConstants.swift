//
//  ActionButtonsConstants.swift
//  iosApp
//
//  Created by evilgen on 18.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import CoreGraphics

enum ActionButtonsConstants {
    //    MARK: - Strings
    enum Strings {
        //    MARK: Titles
        enum Titles {
            static let generalTitle = "Добавить "
            static let taskTitle = generalTitle + "задачу"
            static let subTaskTitle = generalTitle + "подзадачу"
            static let laborCostTitle = generalTitle + "трудозатраты"
            static let attachmentTitle = generalTitle + "вложение"
        }

        //    MARK: Image Names
        enum ImageNames {
            static let taskImageName = "plus"
            static let subTaskImageName = taskImageName
            static let attachmentImageName = taskImageName
        }
    }
}
