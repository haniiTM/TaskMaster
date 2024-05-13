import android.content.Context
import android.content.SharedPreferences

object AppSettings {
    private const val PREF_NAME = "MyAppSettings"
    private const val DARK_THEME_KEY = "DarkTheme"

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
}