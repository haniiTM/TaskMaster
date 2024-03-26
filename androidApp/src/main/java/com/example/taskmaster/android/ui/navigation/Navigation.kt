package com.example.taskmaster.android.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.taskmaster.android.ui.screens.login_screen.LoginScreen

import io.github.aakira.napier.Napier

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {



    NavHost(
        navController = navController,
        startDestination = NavigationItem.Login.route
    ) {

        composable(route = NavigationItem.Login.route) {
            LoginScreen(navController = navController)
        }
    }
}
