package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.ButtonTemplate
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.projectTemplate.CompletedTasksContainer
import com.example.taskmaster.android.ui.component.projectTemplate.UncompletedTasksContainer
import com.example.taskmaster.android.ui.component.taskInfoItems.TaskDescription

@Composable
fun ProjectSubTaskScreen(navController: NavController, id: Int?, title: String?, taskTitle: String?, taskDescription: String?) {
    Box {
        Column(modifier = Modifier.fillMaxWidth()) {
            Header(
                text = title ?: "Заголовок отсутствует",
                iconItem = 0,
                actionIcons = listOf(),
                navController,
                spacer = false
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ButtonTemplate(
                        id = id,
                        navController = navController,
                        text = taskTitle!!,
                        width = 232,
                        rotateAngle = 0f,
                        title = title ?: "Заголовок отсутствует",
                        iconItem = R.drawable.arrow_circle_right_icon,
                    )
                    TaskDescription(description = taskDescription ?: "Описание отсутствует")
                    ButtonTemplate(
                        navController = navController,
                        text = "Вложения",
                        width = 232,
                        rotateAngle = 0f,
                        title = title ?: "Заголовок не получен",
                        id = id
                    )
                }

                item {
                    UncompletedTasksContainer(
                        title = "Задачи",
                        buttonTitle = "Добавить задачу",
                        navController = navController,
                        id = id,
                        projectTitle = title ?: "Заголовок отсутствует"
                    )
                }

                item {
                    CompletedTasksContainer(
                        title = "Выполнено",
                        buttonTitle = "",
                        navController = navController,
                        id = id,
                        projectTitle = title ?: "Заголовок отсутствует"
                    )
                }
            }
        }
    }
}