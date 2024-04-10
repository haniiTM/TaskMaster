package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.ButtonTemplate
import com.example.taskmaster.android.ui.component.projectTemplate.CardContainer
import com.example.taskmaster.android.ui.component.taskInfoItems.TaskDescription

@Composable
fun ProjectSubTaskScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            ButtonTemplate(text = "Название задачи", width = 232, rotateAngle = 0f, navController = navController, iconItem = R.drawable.arrow_circle_right_icon, route = "taskInfo")
            TaskDescription(description = "Подзадачи какой-то задачи, какого-то проекта")
            ButtonTemplate(text = "Вложения", width = 232, rotateAngle = 0f, navController = navController, route = "attachmentsList")
        }
        itemsIndexed(
            listOf(
                "Задачи" to "Добавить задачу",
                "Выполнено" to "Перенести задачу"
            )
        ) { _, (title, buttonTitle) ->
            CardContainer(
                title = title,
                buttonTitle = buttonTitle,
                navController = navController,
                id = 28
            )
        }
    }
}