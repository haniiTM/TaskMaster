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
    @Environment(\.colorScheme) var colorScheme
    @EnvironmentObject var themeManager: ThemeManager
    @EnvironmentObject var userRoleManager: UserRoleManager
    @EnvironmentObject var authManager: AuthManager

    @StateObject private var viewModel = LoginScreenViewModel()
    @State private var loginTextFieldState = "user1"
    @State private var passwordTextFieldState = "22"
    @State private var isPasswordPresented = false
    @State private var isErrorPresented = false

    //    MARK: Body
    var body: some View {
        NavigationView {
            ViewBody
                .toolbar {
                    ToolbarItem(placement: .topBarTrailing) {
                        Button(action: { themeManager.isDarkThemeActive.toggle() }) {
                            Image(systemName: colorScheme == .dark ? "sun.min" : "moon.circle")
                        }
                    }
                }
        }
    }

    private var ViewBody: some View {
        VStack(spacing: LoginScreenConstants.Numbers.rootVerticalSpacing) {
            Logo(appIcon: AppIconProvider.appIcon())

            VStack(spacing: LoginScreenConstants.Numbers.childVerticalSpacing) {
                LoginActionsBody

                errorTextBody
            }
        }.padding(LoginScreenConstants.Numbers.rootPadding)
    }

    private var LoginActionsBody: some View {
        Group {
            TextFieldsBody

            loginButtonBody
        }
        .font(.subheadline)
        .background(
            Color(uiColor: .secondarySystemBackground),
            in: RoundedRectangle(cornerRadius: 8, style: .continuous)
        )
    }

    private var TextFieldsBody: some View {
        Group {
            TextField(text: $loginTextFieldState) {
                Text(LoginScreenConstants.Strings.loginTextFieldTitle)
            }

            secureFieldBody
        }
        .padding()
        .padding(.horizontal)
    }

    private var secureFieldBody: some View {
        HStack {
            if isPasswordPresented {
                TextField(text: $passwordTextFieldState) {
                    Text(LoginScreenConstants.Strings.passwordTextFieldTitle)
                }
            } else {
                SecureField(text: $passwordTextFieldState) {
                    Text(LoginScreenConstants.Strings.passwordTextFieldTitle)
                }
            }

            Button {
                isPasswordPresented.toggle()
            } label: {
                Image(systemName: isPasswordPresented ? "eye.slash" : "eye")
            }

        }
    }

    private var loginButtonBody: some View {
        Button(
            action: {
                Task {
                    let response = await viewModel.loginUser(name: loginTextFieldState,
                                                                    password: passwordTextFieldState)

                    if response.isValid {
                        isErrorPresented = false
                        loginTextFieldState = ""
                        passwordTextFieldState = ""
                        userRoleManager.isAdmin = response.isAdmin
                        authManager.isAuthenticated = true
                    } else {
                        isErrorPresented = true
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

    @ViewBuilder
    private var errorTextBody: some View {
        if isErrorPresented {
            Text("Неверный логин или пароль")
                .foregroundColor(.pink)
        }
    }
}
