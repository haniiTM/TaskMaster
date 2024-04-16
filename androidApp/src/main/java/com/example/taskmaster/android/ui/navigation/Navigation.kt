package com.example.taskmaster.android.ui.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskmaster.android.ui.screens.AttachmentsListScreen
import com.example.taskmaster.android.ui.screens.CalculationOfLaborCosts
import com.example.taskmaster.android.ui.screens.ProjectScreen
import com.example.taskmaster.android.ui.screens.ProjectSubTaskScreen
import com.example.taskmaster.android.ui.screens.ProjectTaskScreen
import com.example.taskmaster.android.ui.screens.TaskInfoScreen
import com.example.taskmaster.android.ui.screens.TaskLaborCostListScreen
import com.taskmaster.ui.screens.AuthScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.TaskInfo.route
    ) {

        composable(route = NavigationItem.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(route = NavigationItem.Projects.route) {
            ProjectScreen(navController = navController)
        }

        composable(
            route = NavigationItem.ProjectTask.route,
            arguments = listOf(navArgument(PROJECT_TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(PROJECT_TASK_ARGUMENT_KEY)
            ProjectTaskScreen(navController = navController, id = id)
        }

        composable(route = NavigationItem.ProjectSubTask.route) {
            ProjectSubTaskScreen(navController = navController)
        }
        composable(route = NavigationItem.TaskInfo.route) {
            TaskInfoScreen(navController = navController)
        }
        composable(route = NavigationItem.TaskLaborCostListScreen.route) {
            TaskLaborCostListScreen(navController = navController)
        }
        composable(route = NavigationItem.AttachmentsListScreen.route) {
            AttachmentsListScreen(navController = navController)
        }
        composable(route = NavigationItem.CalculationOfLaborCosts.route) {
            CalculationOfLaborCosts(navController = navController)
        }
    }
}