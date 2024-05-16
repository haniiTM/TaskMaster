package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.projectTemplate.BoxButton
import com.example.taskmaster.android.ui.component.taskInfoItems.ListItemList

@Composable
fun AttachmentsListScreen(navController: NavController, id: Int?, title: String?){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        ListItemList(taskId = id ?: 0, attachmentsListFlag = true)
        BoxButton(text = "Добавить вложение", cardContainerFlag = false){

        }
    }

}