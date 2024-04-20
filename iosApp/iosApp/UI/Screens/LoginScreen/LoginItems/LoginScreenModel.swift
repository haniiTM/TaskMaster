//
//  LoginScreenModel.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

struct LoginScreenModel {
    //    MARK: Props
    var userList: [UserInfo] { privateUserList }
    private var privateUserList: [UserInfo] = .init()

    //    MARK: Init
    init() {
        setupDefaultUsers()
    }

    //    MARK: Methods
    private mutating func setupDefaultUsers() {
        let admin = UserInfo(id: .init(), name: "admin", password: "admin")
        privateUserList = [admin]
    }

    func loginUser(name: String, password: String) throws {
        try userList.forEach { user in
            if user.name == name {
                if user.password == password {
//                    ????
                } else {
                    throw LoginError.passwordDoesntMatch
                }
            } else {
                throw LoginError.userNotFound
            }
        }
    }
}
