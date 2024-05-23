//
//  LoginScreenViewModel.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation
import shared

@MainActor final class LoginScreenViewModel: ObservableObject {
    //    MARK: Props
    private let accessTokenDtoUseCase = KoinHelper().getAccessTokenDtoUseCase()

    //    MARK: Methods
    func loginUser(name: String, password: String) async -> Bool {
        do {
            guard
                let tokenDto = try await accessTokenDtoUseCase.fetchUserToken(login: name,
                                                                              password: password),
                let token = tokenDto.tokenLong
            else { return false }

            return token.isEmpty ? false : true
        } catch {
            print(error.localizedDescription)
            return false
        }
    }
}
