package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.taskInfoItems.TaskInfoBlock

@Composable
fun TaskInfoScreen(navController: NavController, id: Int?, title: String){
    Column {
        Header(
            text = title ?: "Заголовок отсутствует",
            iconItem = 0,
            actionIcons = listOf(),
            navController,
            spacer = false)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            TaskInfoBlock(navController = navController)
        }
    }
}