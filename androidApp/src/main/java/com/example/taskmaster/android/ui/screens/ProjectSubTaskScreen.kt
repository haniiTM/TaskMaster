package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.ButtonTemplate
import com.example.taskmaster.android.ui.component.CardContainer
import com.example.taskmaster.android.ui.component.TaskDescription
import com.example.taskmaster.android.ui.component.TaskInfoBlock

@Composable
fun ProjectSubTaskScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            TaskDescription(description = "Подзадачи какой-то задачи, какого-то проекта")
            ButtonTemplate(text = "Вложения", width = 232, rotateAngle = 0f)
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
                navController = navController
            )
        }
    }
}