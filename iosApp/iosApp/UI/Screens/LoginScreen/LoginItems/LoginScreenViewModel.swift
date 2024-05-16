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
    private let accessTokenDtoUseCase = KoinHelper().getAccessTokenDtoUseCase()

    @Published var isTokenValid = false
//    var openProjectsViewSignal: (() -> Void)?

    func loginUser(name: String, password: String) async {
        do {
            guard
                let token = try await accessTokenDtoUseCase.fetchUserToken(login: name,
                                                                              password: password),
                let isTokenEmtpy = token.tokenLong
            else { return }

            //            !isTokenEmtpy.isEmpty ? print("Error") : openProjectsViewSignal?()
            !isTokenEmtpy.isEmpty ? print("Error") : isTokenValid.toggle()

        } catch {
            print(error.localizedDescription)
        }
    }
}
