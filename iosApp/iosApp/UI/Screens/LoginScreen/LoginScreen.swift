//
//  LoginScreen.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct LoginScreen: View {
    @State private var loginTextFieldState = ""
    @State private var passwordTextFieldState = ""
    
    private let loginTextFieldTitle = "Login"
    private let passwordTextFieldTitle = "Password"
    private let loginButtonTitle = "Log In"
    
    var body: some View {
        LoginScreenBody()
    }
    
    private func LoginScreenBody() -> some View {
        VStack {
            Logo()
            
            TextField(loginTextFieldTitle, text: $loginTextFieldState)
            TextField(passwordTextFieldTitle, text: $passwordTextFieldState)
            
            Button(loginButtonTitle) {
                
            }
        }
    }
}

#Preview {
    LoginScreen()
}
