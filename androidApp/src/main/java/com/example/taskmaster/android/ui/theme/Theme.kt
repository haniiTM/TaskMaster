package com.example.taskmaster.android.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    secondary = Ultramarine,
    onPrimary = Blueberry,
    onSecondary = Black,
    tertiary = Blueberry,
    surface = LightRed,
    surfaceVariant = Lime,
    primaryContainer = Gray,
    outline = Black,
    error = Red,
    surfaceTint = DarkPurple,
    onTertiary = White,
    scrim = Black
)

private val LightColorScheme = lightColorScheme(
    primary = DeepBlue,
    secondary = White,
    onPrimary = White,
    onSecondary = Crayola,
    tertiary = Crayola,
    surface = LightRed,
    surfaceVariant = Lime,
    primaryContainer = Gray,
    outline = Black,
    error = Red,
    surfaceTint = RoyalBlue,
    onTertiary = Black,
    scrim = Gray
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val statusBarColor = MaterialTheme.colorScheme.error
    val colors = if (!useDarkTheme) {
        LightColorScheme
    } else {
        DarkColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb() // change color status bar here
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}