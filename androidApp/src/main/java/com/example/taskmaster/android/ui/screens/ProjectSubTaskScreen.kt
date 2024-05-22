package com.example.taskmaster.android.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun ProjectSubTaskScreen(
    navController: NavController,
    id: Int?,
    title: String?,
    taskTitle: String?,
    taskDescription: String?,
) {
    Log.d("taskId",id.toString())
    var description by remember {
        mutableStateOf("")
    }
    Box {
        Column(modifier = Modifier.fillMaxWidth()) {
            Header(
                text = title ?: "Заголовок отсутствует",
                actionIcons = listOf(
                    R.drawable.search1_icon, R.drawable.users_icon
                ),
                navController = navController,
                actionTitle = listOf("Поиск", "Пользователи"),
                projectId = id
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
                        iconItem = R.drawable.arrow_circle_right_icon
                    )
                    TaskDescription(description = taskDescription, taskId = id ?: 0, onValueChange = {newValue -> description = newValue})
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