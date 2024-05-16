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
    @Published var isTokenValid = false

    //    MARK: Methods
    func loginUser(name: String, password: String) async {
        do {
            guard
                let tokenDto = try await accessTokenDtoUseCase.fetchUserToken(login: name,
                                                                              password: password),
                let token = tokenDto.tokenLong
            else { return }

            token.isEmpty ? print("Error: wrong login or password!") : isTokenValid.toggle()

        } catch {
            print(error.localizedDescription)
        }
    }
}
