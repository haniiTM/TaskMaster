package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.component.projectTemplate.CalculationTimeButton
import com.example.taskmaster.android.ui.component.projectTemplate.CompletedTasksContainer
import com.example.taskmaster.android.ui.component.projectTemplate.UncompletedTasksContainer

@Composable
fun ProjectTaskScreen(navController: NavController, id: Int?, title: String?) {
    var searchText by remember { mutableStateOf("") }
    var showSearchLine by remember {
        mutableStateOf(false)
    }
    Column {
        Header(
            text = title ?: "Заголовок отсутствует",
            actionIcons = listOf(
                R.drawable.search1_icon, R.drawable.users_icon
            ),
            navController = navController,
            actionTitle = listOf("Поиск", "Пользователи"),
            projectScreenKey = false,
            projectId = id,
            activeMenu = true,
            showSearchLine = { showSearchLine = true })
        if (showSearchLine) {
            Box(modifier = Modifier.padding(start = 14.dp, end = 14.dp, bottom = 10.dp)) {
                UnifiedTextBox(
                    value = searchText,
                    onValueChange = { newValue -> searchText = newValue },
                    roundedDownAngle = 15,
                    roundedTopAngle = 15,
                    placeholder = "Поиск"
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CalculationTimeButton(projectId = id, navController = navController, title = title)
            }

            item {
                UncompletedTasksContainer(
                    title = "Задачи",
                    buttonTitle = "Добавить задачу",
                    navController = navController,
                    id = id,
                    projectTitle = title ?: "Заголовок отсутствует",
                    searchText = searchText
                )
            }

            item {
                CompletedTasksContainer(
                    title = "Выполнено",
                    buttonTitle = "",
                    navController = navController,
                    id = id,
                    projectTitle = title ?: "Заголовок отсутствует",
                    searchText = searchText
                )
            }
        }
    }
}

