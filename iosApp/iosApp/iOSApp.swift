import SwiftUI
import shared

@main
struct iOSApp: App {
    @StateObject private var authManager = AuthManager()
    @StateObject private var themeManager = ThemeManager()
    @StateObject private var userRoleManager = UserRoleManager()

    init() {
        AppModuleKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            StartView()
                .tint(.init(uiColor: .systemBlue))
                .preferredColorScheme(themeManager.isDarkThemeActive ? .dark : .light)
                .environmentObject(authManager)
                .environmentObject(themeManager)
                .environmentObject(userRoleManager)
        }
    }
}

// MARK: - Environment Objects
final class AuthManager: ObservableObject {
    @Published var isAuthenticated = false
}

final class ThemeManager: ObservableObject {
    @Published var isDarkThemeActive = true
}

final class UserRoleManager: ObservableObject {
    @Published var isAdmin = false
}
