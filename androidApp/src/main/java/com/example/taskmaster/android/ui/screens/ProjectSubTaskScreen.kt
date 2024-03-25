package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.CardContainer
import com.example.taskmaster.android.ui.component.TaskDescription
import com.example.taskmaster.android.ui.component.TaskInfoBlock

@Composable
fun ProjectSubTaskScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item { TaskDescription(description = "Подзадачи какой-то задачи, какого-то проекта") }
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