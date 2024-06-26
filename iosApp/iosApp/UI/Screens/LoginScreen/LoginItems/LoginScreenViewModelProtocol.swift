//
//  LoginScreenViewModelProtocol.swift
//  iosApp
//
//  Created by evilgen on 20.04.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import Foundation

protocol LoginScreenViewModelProtocol: AnyObject {
    //    MARK: Signals
    var openProjectsViewSignal: (() -> Void)? { get set }

    //    MARK: Methods
    func loginUser(name: String, password: String) async 
}
