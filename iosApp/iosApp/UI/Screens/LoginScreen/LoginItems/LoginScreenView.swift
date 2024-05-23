//
//  LoginScreenView.swift
//  iosApp
//
//  Created by evilgen on 25.03.2024.
//  Copyright © 2024 TaskMaster. All rights reserved.
//

import SwiftUI
import shared

struct LoginScreenView: View {
    //    MARK: Props
    @EnvironmentObject var authViewModel: AuthViewModel
    @StateObject private var viewModel = LoginScreenViewModel()

    @State private var loginTextFieldState = "user1"
    @State private var passwordTextFieldState = "22"

    //    MARK: Body
    var body: some View {
        ViewBody
    }

    private var ViewBody: some View {
        VStack(spacing: LoginScreenConstants.Numbers.rootVerticalSpacing) {
            Logo(appIcon: AppIconProvider.appIcon())

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
                action: {
                    Task {
                        let isAuthenticated = await viewModel.loginUser(name: loginTextFieldState,
                                                                        password: passwordTextFieldState)

                        if isAuthenticated {
                            loginTextFieldState = ""
                            passwordTextFieldState = ""
                            authViewModel.isAuthenticated = true
                        } else {
                            print("Eror: incorrect login or password!")
                        }
                    }
                },

                label: {
                    Text(LoginScreenConstants.Strings.loginButtonTitle)
                        .tint(.primary)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .padding(.horizontal)
                        .background(.ultraThinMaterial)
                }
            )
        }
        .font(.subheadline)
        .background(
            Color(uiColor: .secondarySystemBackground),
            in: RoundedRectangle(cornerRadius: 8, style: .continuous)
        )
    }
}
