package com.example.taskmaster.android.ui.component.commonTemplate

import AppSettings
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmaster.android.R

@Composable
fun ThemeChangingButton(darkTheme: Boolean, onThemeUpdate: () -> Unit, navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val padding = if (currentRoute == "auth"){
        16
    }else 0
    IconButton(
        onClick = {
            onThemeUpdate()
            AppSettings.setDarkTheme(navController.context, !darkTheme) // Сохраняем состояние darkTheme
        }, modifier = Modifier.padding(start = padding.dp)
    ) {
        Icon(
            painter = painterResource(id = if (darkTheme) R.drawable.light_theme_icon else R.drawable.dark_theme_icon),
            contentDescription = "Theme icon", tint = if(currentRoute == "auth"){ MaterialTheme.colorScheme.onTertiary} else Color.Black,
        )
    }
}