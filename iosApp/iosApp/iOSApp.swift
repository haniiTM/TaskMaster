import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        AppModuleKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            LoginScreenView()
                .tint(.init(uiColor: .systemBlue))
        }
    }
}

/* MARK: - every screen nav bar actions:
 - theme switcher
 - search
 */

/* MARK: - not proj view but every other:
 ...
 - users
 */

/* MARK: - proj view:
 ...
 - add user
 - del user
 - add proj
 - quit
 */
