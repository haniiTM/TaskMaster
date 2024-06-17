//
//  LoginScreenViewModel.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

@MainActor
final class LoginScreenViewModel: ObservableObject {
    //    MARK: Props
    @AppStorage("userToken") private var userToken: String?
    private let accessTokenDtoUseCase = KoinHelper().getAccessTokenDtoUseCase()

    //    MARK: Methods
    func loginUser(name: String, password: String) async -> (isValid: Bool, isAdmin: Bool) {
        do {
            guard
                let tokenDto = try await accessTokenDtoUseCase.fetchUserToken(login: name,
                                                                              password: password),
                let token = tokenDto.tokenLong,
                let userRole = tokenDto.adminOrProjectManager as? Bool
            else { return (false, false) }

            userToken = token

            return (token.isEmpty ? false : true, userRole)
        } catch {
            print(error.localizedDescription)
            return (false, false)
        }
    }
}
