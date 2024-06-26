package com.example.taskmaster.android.ui.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskmaster.android.ui.component.StateObject.AuthValidObject
import com.example.taskmaster.android.ui.screens.AttachmentsListScreen
import com.example.taskmaster.android.ui.screens.CalculationOfLaborCostsScreen
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
        startDestination = if (AuthValidObject.AuthValid) NavigationItem.Projects.route else NavigationItem.Auth.route
    ) {

        composable(route = NavigationItem.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(
            route = NavigationItem.Projects.route, arguments = listOf(navArgument(
                USER_ROLE_ARGUMENT_KEY
            ) {
                type = NavType.BoolType
            })
        ) { backStackEntry ->
            val result = backStackEntry.arguments?.getBoolean(USER_ROLE_ARGUMENT_KEY)
            ProjectScreen(navController = navController, result = result!!)
        }

        composable(
            route = NavigationItem.ProjectTask.route,
            arguments = listOf(navArgument(PROJECT_TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }, navArgument(PROJECT_TITLE_ARGUMENT_KEY) {
                type = NavType.StringType
            },navArgument(PROJECT_ID_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(PROJECT_TASK_ARGUMENT_KEY)
            val title = backStackEntry.arguments?.getString(PROJECT_TITLE_ARGUMENT_KEY)
            val projectId = backStackEntry.arguments?.getInt(PROJECT_ID_ARGUMENT_KEY)

            ProjectTaskScreen(navController = navController, id = id, title = title, projectId)
        }

        composable(
            route = NavigationItem.ProjectSubTask.route,
            arguments = listOf(navArgument(PROJECT_SUBTASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }, navArgument(PROJECT_TITLE_ARGUMENT_KEY) {
                type = NavType.StringType
            }, navArgument(TASK_TITLE_ARGUMENT_KEY) {
                type = NavType.StringType
            }, navArgument(TASK_DESCRIPTION_ARGUMENT_KEY) {
                type = NavType.StringType
            }, navArgument(PROJECT_ID_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(PROJECT_SUBTASK_ARGUMENT_KEY)
            val title = backStackEntry.arguments?.getString(PROJECT_TITLE_ARGUMENT_KEY)
            val taskTitle = backStackEntry.arguments?.getString(TASK_TITLE_ARGUMENT_KEY)
            val taskDescription = backStackEntry.arguments?.getString(TASK_DESCRIPTION_ARGUMENT_KEY)
            val projectId = backStackEntry.arguments?.getInt(PROJECT_ID_ARGUMENT_KEY)
            ProjectSubTaskScreen(
                navController = navController,
                id = id,
                title = title,
                taskTitle = taskTitle,
                taskDescription = taskDescription,
                projectId = projectId
            )
        }

        composable(
            route = NavigationItem.TaskInfo.route,
            arguments = listOf(navArgument(PROJECT_INFO_ARGUMENT_KEY) {
                type = NavType.IntType
            }, navArgument(PROJECT_TITLE_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(PROJECT_INFO_ARGUMENT_KEY)
            val title = backStackEntry.arguments?.getString(PROJECT_TITLE_ARGUMENT_KEY)
            TaskInfoScreen(
                navController = navController,
                id = id,
                title = title
            )
        }
        composable(
            route = NavigationItem.TaskLaborCostListScreen.route,
            arguments = listOf(navArgument(PROJECT_INFO_ARGUMENT_KEY) {
                type = NavType.IntType
            }, navArgument(PROJECT_TITLE_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(PROJECT_INFO_ARGUMENT_KEY)
            val title = backStackEntry.arguments?.getString(PROJECT_TITLE_ARGUMENT_KEY)
            TaskLaborCostListScreen(
                navController = navController,
                id = id,
                title = title
            )
        }
        composable(
            route = NavigationItem.AttachmentsListScreen.route,
            arguments = listOf(navArgument(PROJECT_INFO_ARGUMENT_KEY) {
                type = NavType.IntType
            }, navArgument(PROJECT_TITLE_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(PROJECT_INFO_ARGUMENT_KEY)
            val title = backStackEntry.arguments?.getString(PROJECT_TITLE_ARGUMENT_KEY)
            AttachmentsListScreen(
                navController = navController,
                id = id,
                title = title
            )
        }
        composable(
            route = NavigationItem.CalculationOfLaborCosts.route, arguments = listOf(navArgument(
                PROJECT_TASK_ARGUMENT_KEY
            ) {
                type = NavType.IntType
            },
                navArgument(PROJECT_TITLE_ARGUMENT_KEY) {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val result = backStackEntry.arguments?.getInt(PROJECT_TASK_ARGUMENT_KEY)
            val title = backStackEntry.arguments?.getString(PROJECT_TITLE_ARGUMENT_KEY)
            CalculationOfLaborCostsScreen(navController = navController, result = result, title = title)
        }
    }
}