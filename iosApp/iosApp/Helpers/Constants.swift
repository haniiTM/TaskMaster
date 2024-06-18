//
//  Constants.swift
//  TaskMaster
//
//  Created by evilgen on 24.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

enum Constants {
    //    MARK: - Strings
    enum Strings {
        //    MARK: Image names
        enum ImageNames {
            static let extraActionsImageName = "ellipsis"
            static let searchActionImageName = "rectangle.and.text.magnifyingglass"
        }

        enum DestructiveAlertMessage {
            static let warningTitle = "Вы уверены?"
            static let confirmationActionTitle = "Да"
            static let denialActionTitle = "Нет"
        }
    }
}

extension Color {
    static let lightRed = Color(red: 249/255, green: 108/255, blue: 108/255)
    static let deepBlue = Color(red: 0/255, green: 50/255, blue: 158/255)
    static let crayola = Color(red: 140/255, green: 160/255, blue: 233/255)
    static let royalBlue = Color(red: 79/255, green: 112/255, blue: 233/255)
    static let gray = Color(red: 217/255, green: 217/255, blue: 217/255)
    static let shadowGray = Color(red: 207/255, green: 207/255, blue: 207/255)
    static let black = Color(red: 0/255, green: 0/255, blue: 0/255)
    static let white = Color(red: 255/255, green: 255/255, blue: 255/255)
    static let lime = Color(red: 87/255, green: 215/255, blue: 92/255)
    static let red = Color(red: 255/255, green: 0/255, blue: 0/255)
    static let darkGray = Color(red: 77/255, green: 77/255, blue: 77/255)
    static let ultramarine = Color(red: 42/255, green: 21/255, blue: 166/255)
    static let blueberry = Color(red: 89/255, green: 60/255, blue: 255/255)
    static let darkPurple = Color(red: 33/255, green: 25/255, blue: 80/255)
    static let placeholder = Color(red: 190/255, green: 190/255, blue: 192/255)
}

extension Image {
    static let addIcon = Image("add_icon")
    static let addProjectIcon = Image("add_project_icon")
    static let addUserIcon = Image("add_user_icon")
    static let administratorRoleIcon = Image("administrator_role_icon")
    static let arrowCircleRightIcon = Image("arrow_circle_right_icon")
    static let backendRoleIcon = Image("backend_role_icon")
    static let calendarIcon = Image("calendar_icon")
    static let cancelIcon = Image("cancel_icon")
    static let clearIcon = Image("clear_icon")
    static let clockIcon = Image("clock_icon")
    static let crossedOutEyeIcon = Image("crossed_out_eye_icon")
    static let darkThemeIcon = Image("dark_theme_icon")
    static let deleteIcon = Image("delete_icon")
    static let designerRoleIcon = Image("designer_role_icon")
    static let doneIcon = Image("done_icon")
    static let downloadIcon = Image("download_icon")
    static let exitIcon = Image("exit_icon")
    static let eyeIcon = Image("eye_icon")
    static let frontendRoleIcon = Image("frontend_role_icon")
    static let lightThemeIcon = Image("light_theme_icon")
    static let more = Image("more")
    static let projectManagerRoleIcon = Image("project_manager_role_icon")
    static let removeUserIcon = Image("remove_user_icon")
    static let search1Icon = Image("search1_icon")
    static let soloUserIcon = Image("solo_user_icon")
    static let testerRoleIcon = Image("tester_role_icon")
    static let usersGroupIcon = Image("users_group_icon")
    static let usersIcon = Image("users_icon")
    static let warning = Image("warning")
}
