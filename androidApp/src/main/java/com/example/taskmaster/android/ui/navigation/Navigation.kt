package com.example.taskmaster.android.ui.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.taskmaster.ui.screens.AuthScreen
import com.example.taskmaster.android.ui.screens.ProjectScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Auth.route
    ) {

        composable(route = NavigationItem.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(route = NavigationItem.Projects.route) {
            ProjectScreen(navController = navController)
        }
    }
}