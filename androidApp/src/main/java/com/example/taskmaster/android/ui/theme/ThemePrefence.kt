import android.content.Context
import android.content.SharedPreferences

object AppSettings {
    private const val PREF_NAME = "MyAppSettings"
    private const val DARK_THEME_KEY = "DarkTheme"
    private const val LOGIN_VALID_KEY = "Auth"

    fun setDarkTheme(context: Context, darkTheme: Boolean) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        preferences.edit().putBoolean(DARK_THEME_KEY, darkTheme).apply()
    }

    fun getDarkTheme(context: Context): Boolean {
        val preferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(DARK_THEME_KEY, false)
    }

    fun getLoginValid(context: Context): Boolean {
        val preferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(LOGIN_VALID_KEY, false)
    }
    fun setLoginValid(context: Context, loginValid : Boolean) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        preferences.edit().putBoolean(LOGIN_VALID_KEY, loginValid).apply()
    }
}