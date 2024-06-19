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
    @State private var loginTextFieldState = ""
    @State private var passwordTextFieldState = ""
    @State private var isPasswordPresented = false
    @State private var isErrorPresented = false

    //    MARK: Body
    var body: some View {
        NavigationView {
            ZStack {
                GradientBG()

                ViewBody
                    .toolbar {
                        ToolbarItem(placement: .topBarLeading) {
                            Button {
                                themeManager.isDarkThemeActive.toggle()
                            } label: {
                                colorScheme == .dark
                                ? Image.lightThemeIcon
                                : Image.darkThemeIcon
                            }
                            .foregroundColor(colorScheme == .dark
                                             ? .white
                                             : .black)
                            .padding()
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
        .foregroundColor(.black)
    }

    @ViewBuilder
    private var TextFieldsBody: some View {
        TextField(text: $loginTextFieldState) {
            Text(LoginScreenConstants.Strings.loginTextFieldTitle)
        }
        .padding(12)
        .background(.white)
        .clipShape(TopRoundedCorners(radius: 8))

        secureFieldBody
            .padding(12)
            .background(.white)
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
                isPasswordPresented ? Image.crossedOutEyeIcon : Image.eyeIcon
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
                        passwordTextFieldState = ""
                        isErrorPresented = true
                    }
                }
            },

            label: {
                Text(LoginScreenConstants.Strings.loginButtonTitle)
                    .frame(maxWidth: .infinity)
                    .padding(12)
                    .padding(.horizontal)
                    .background(.white)
                    .clipShape(BottomRoundedCorners(radius: 8))
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

struct TopRoundedCorners: Shape {
    var radius: CGFloat = .infinity

    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.addRoundedRect(in: CGRect(x: rect.minX, y: rect.minY, width: rect.width, height: rect.height),
                            cornerSize: CGSize(width: radius, height: radius),
                            style: .circular)
        path.addRect(CGRect(x: rect.minX, y: rect.maxY - radius, width: rect.width, height: radius))
        return path
    }
}

struct BottomRoundedCorners: Shape {
    var radius: CGFloat = .infinity

    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.addRoundedRect(in: CGRect(x: rect.minX, y: rect.minY, width: rect.width, height: rect.height),
                            cornerSize: CGSize(width: radius, height: radius),
                            style: .circular)
        path.addRect(CGRect(x: rect.minX, y: rect.minY, width: rect.width, height: radius))
        return path
    }
}
