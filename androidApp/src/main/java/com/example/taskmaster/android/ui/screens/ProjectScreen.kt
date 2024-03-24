package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.CardContainer

@Composable
fun ProjectScreen(navController: NavController) {
    LazyColumn() {
        itemsIndexed(
            listOf(
                "Задачи" to "Добавить задачу",
                "Выполнено" to "Перенести задачу"
            )
        ) { _, (title, buttonTitle) ->
            CardContainer(title = title, buttonTitle = buttonTitle)
        }
    }
}