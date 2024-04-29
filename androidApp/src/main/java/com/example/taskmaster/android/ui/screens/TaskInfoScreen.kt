package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.taskInfoItems.TaskInfoBlock
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.data.network.models.TaskByID
import com.example.taskmaster.data.network.models.TaskDTO
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskInfoScreen(
    navController: NavController,
    id: Int?,
    title: String?,
    viewTaskModel: TaskViewModel = getViewModel()
) {
    var task: TaskByID? by remember { mutableStateOf(null) }

    LaunchedEffect(id) {
        viewTaskModel.dataTaskById(id!!).observeForever { taskValue ->
            task = taskValue
        }
    }

    Column {
        Header(
            text = title ?: "Заголовок отсутствует",
            iconItem = 0,
            actionIcons = listOf(),
            navController,
            spacer = false
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (task != null) {
                TaskInfoBlock(
                    navController = navController,
                    id = id,
                    title = title,
                    name = task?.name ?: "",
                    scope = task?.score ?: 0,
                    status = task?.status ?: 0,
                    spentTime = task?.spentTime ?: 0,
                    spentedTime =  task?.spentedTime ?: "",
                    typeofactivityid = task?.typeofactivityid ?: 0,
                    userCount = task?.userCount ?: 0
                )
            } else {
                Text("Loading...")
            }
        }
    }
}