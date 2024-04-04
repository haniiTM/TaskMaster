package com.example.taskmaster.android.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.projectTemplate.CalculationTimeButton
import com.example.taskmaster.android.ui.component.projectTemplate.CardContainer

@Composable
fun ProjectTaskScreen(navController: NavController, id: Int?) {
    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        item{ CalculationTimeButton() }
        itemsIndexed(
            listOf(
                "Задачи" to "Добавить задачу",
                "Выполнено" to ""
            )
        ) { _, (title, buttonTitle) ->
            CardContainer(
                title = title,
                buttonTitle = buttonTitle,
                navController = navController,
                id = id
            )
            Log.d("id", navController.toString())
        }
    }
}
