//
//  LoginScreenView.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI

struct LoginScreenView: View {
    //    MARK: Props
    private let viewModel: LoginScreenViewModelProtocol = LoginScreenViewModel()
    @State private var loginTextFieldState = ""
    @State private var passwordTextFieldState = ""

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        VStack(spacing: LoginScreenConstants.Numbers.rootVerticalSpacing) {
            Logo()

            VStack(spacing: LoginScreenConstants.Numbers.childVerticalSpacing) {
                LoginActionsBody
            }
        }.padding(LoginScreenConstants.Numbers.rootPadding)
    }

    private var TextFieldsBody: some View {
        Group {
            TextField(text: $loginTextFieldState) {
                Text(LoginScreenConstants.Strings.loginTextFieldTitle)
            }

            TextField(text: $passwordTextFieldState) {
                Text(LoginScreenConstants.Strings.passwordTextFieldTitle)
            }
        }
        .padding()
        .padding(.horizontal)
    }

    private var LoginActionsBody: some View {
        Group {
            TextFieldsBody

            Button(
                action: { viewModel.loginUser(name: loginTextFieldState,
                                              password: passwordTextFieldState) }
            ) {
                Text(LoginScreenConstants.Strings.loginButtonTitle)
                    .frame(maxWidth: .infinity)
                    .padding()
                    .padding(.horizontal)
            }
        }
        .font(.subheadline)
        .border(Color.primary)
    }
}

#Preview {
    LoginScreenView()
}
