package com.taskmaster.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.authScreenItems.AuthBlock
import com.example.taskmaster.android.ui.component.StateObject.AppState
import com.example.taskmaster.android.ui.component.commonTemplate.ThemeChangingButton

@Composable
fun AuthScreen(navController: NavController) {
    ThemeChangingButton(darkTheme = AppState.darkTheme, onThemeUpdate = { AppState.darkTheme = !AppState.darkTheme}, navController = navController)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), modifier = Modifier
                .height(138.dp)
                .width(138.dp), contentDescription = "company logo"
        )
        Spacer(modifier = Modifier.padding(bottom = 26.dp))
        AuthBlock(navController = navController)
    }
}