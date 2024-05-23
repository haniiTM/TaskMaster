package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.component.taskInfoItems.ListItemList

@Composable
fun TaskLaborCostListScreen(navController: NavController, id: Int?, title: String?){
    var searchText by remember { mutableStateOf("") }
    var showSearchLine by remember {
        mutableStateOf(false)
    }
    Column {
        Header(
            text = title ?: "Заголовок отсутствует",
            actionIcons = listOf(
                R.drawable.users_icon
            ),
            navController = navController,
            actionTitle = listOf("Пользователи"),
            projectId = id,
            activeMenu = true,
            onShowSearchLineChange = { showSearchLine = !showSearchLine }
        )
        if (showSearchLine) {
            Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                UnifiedTextBox(
                    value = searchText,
                    onValueChange = { newValue -> searchText = newValue },
                    roundedDownLeftAngle = 15,
                    roundedDownRightAngle = 15,
                    roundedTopRightAngle = 15,
                    roundedTopLeftAngle = 15,
                    placeholder = "Поиск"
                )
            }
        }
        ListItemList(taskId = id ?: 0, attachmentsListFlag = false, searchText = searchText)
    }
}