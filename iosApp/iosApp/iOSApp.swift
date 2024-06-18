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
    @AppStorage("isAuthenticated") private var isAuthenticatedKey: Bool?

    @Published var isAuthenticated = false {
        didSet {
            isAuthenticatedKey = isAuthenticated
        }
    }

    init() {
        isAuthenticated = isAuthenticatedKey ?? false
    }
}

final class ThemeManager: ObservableObject {
    @AppStorage("isDarkThemeActive") private var isDarkThemeActiveKey: Bool?

    @Published var isDarkThemeActive = true {
        didSet {
            isDarkThemeActiveKey = isDarkThemeActive
        }
    }

    init() {
        isDarkThemeActive = isDarkThemeActiveKey ?? true
    }
}

final class UserRoleManager: ObservableObject {
    @AppStorage("isAdmin") private var isAdminKey: Bool?

    @Published var isAdmin = false  {
        didSet {
            isAdminKey = isAdmin
        }
    }

    init() {
        isAdmin = isAdminKey ?? false
    }
}
