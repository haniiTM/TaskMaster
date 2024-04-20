//
//  LoginScreenViewModel.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

struct LoginScreenViewModel: LoginScreenViewModelProtocol {
    //    MARK: Props
    private let model = LoginScreenModel()
//    private let router:

    //    MARK: Methods
    func loginUser(name: String, password: String) {
        do {
            try model.loginUser(name: name, password: password)
//            router.openProjectListView()
        } catch let error {
//            router.showLoginAlert()
        }
    }
}
