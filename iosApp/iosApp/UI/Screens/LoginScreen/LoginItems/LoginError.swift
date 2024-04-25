//
//  LoginError.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

enum LoginError: Error, LocalizedError {
    case userNotFound
    case passwordDoesntMatch

    var errorDescription: String? {
        switch self {
        case .userNotFound:
            "user not found"
        case .passwordDoesntMatch:
            "password doesnt match"
        }
    }
}
