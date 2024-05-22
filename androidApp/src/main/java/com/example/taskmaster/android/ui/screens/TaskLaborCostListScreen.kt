package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.taskInfoItems.ListItemList

@Composable
fun TaskLaborCostListScreen(navController: NavController, id: Int?, title: String?){
    Column {
        Header(
            text = title ?: "Заголовок отсутствует",
            actionIcons = listOf(
                R.drawable.search1_icon, R.drawable.users_icon
            ),
            navController = navController,
            actionTitle = listOf("Поиск", "Пользователи"),
            projectId = id,
        )
        ListItemList(taskId = id ?: 0, attachmentsListFlag = false)
    }
}