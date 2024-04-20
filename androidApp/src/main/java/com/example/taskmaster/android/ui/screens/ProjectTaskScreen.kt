package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.projectTemplate.CalculationTimeButton
import com.example.taskmaster.android.ui.component.projectTemplate.ComplitedTasksContainer
import com.example.taskmaster.android.ui.component.projectTemplate.UncomplitedTasksContainer

@Composable
fun ProjectTaskScreen(navController: NavController, id: Int?) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CalculationTimeButton()
        }

        item {
            UncomplitedTasksContainer(
                title = "Задачи",
                buttonTitle = "Добавить задачу",
                navController = navController,
                id = id
            )
        }

        item {
            ComplitedTasksContainer(
                title = "Выполнено",
                buttonTitle = "",
                navController = navController,
                id = id
            )
        }
    }
}
