import SwiftUI
import shared

@main
struct iOSApp: App {
    @StateObject private var authViewModel = AuthViewModel()
    @StateObject private var themeManager = ThemeManager()

    init() {
        AppModuleKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            StartView()
                .tint(.init(uiColor: .systemBlue))
                .preferredColorScheme(themeManager.isDarkThemeActive ? .dark : .light)
                .environmentObject(authViewModel)
                .environmentObject(themeManager)
        }
    }
}

// MARK: - Environment Objects
final class AuthViewModel: ObservableObject {
    @Published var isAuthenticated = false
}

final class ThemeManager: ObservableObject {
    @Published var isDarkThemeActive = true
}
