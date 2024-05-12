package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.taskInfoItems.TaskInfoBlock
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskInfoScreen(
    navController: NavController,
    id: Int?,
    title: String?,
    viewTaskModel: TaskViewModel = getViewModel(),
) {
    val taskData by remember { viewTaskModel.stateTaskById }

    var refresh by remember { mutableStateOf(false) }

    if(refresh) {
        // LaunchedEffect перезагружает данные, когда refresh становится true
        LaunchedEffect(refresh) {
            if (refresh) {
                id?.let {
                    viewTaskModel.dataTaskById(it)  // Загружаем данные
                }
                refresh = false
            }
        }
    } else {
        LaunchedEffect(id) {
            id?.let {
                viewTaskModel.dataTaskById(it)  // Загружаем данные
            }
        }
    }
    // Функция-коллбэкт, которая изменяет refresh
    val triggerRefresh: (Boolean) -> Unit = { newValue ->
        refresh = newValue
    }

    Column {
        Header(
            text = title ?: "Заголовок отсутствует",
            iconItem = R.drawable.more,
            actionIcons = listOf(
                R.drawable.search1_icon, R.drawable.users_icon
            ),
            navController = navController,
            actionTitle = listOf("Поиск", "Пользователи"),
            projectScreenKey = false
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (taskData.isLoading) {  // Отображаем загрузку, если `isLoading` истина
                CircularProgressIndicator()  // Индикатор загрузки
            } else if (taskData.itemTaskState != null) {  // Проверяем, если данные задачи доступны
                val itemTaskState = taskData.itemTaskState
                TaskInfoBlock(
                    navController = navController,
                    id = id,
                    title = title,
                    name = itemTaskState?.name ?: "",
                    scope = itemTaskState?.score ?: 0,
                    status = itemTaskState?.status ?: 0,
                    spentTime = itemTaskState?.spentTime ?: 0,
                    spentedTime = itemTaskState?.spentedTime ?: "",
                    typeofactivityid = itemTaskState?.typeofactivityid ?: 0,
                    userCount = itemTaskState?.userCount ?: 0,
                    canAddManHours = itemTaskState?.canAddManHours,
                    projectId = itemTaskState?.projectId ?: 0,
                    triggerRefresh = triggerRefresh,
                    taskDependenceOn = itemTaskState?.taskDependenceOn?.name ?: ""
                )
            } else {
                Text("No Data Available")  // Если данных нет
            }
        }
    }
}