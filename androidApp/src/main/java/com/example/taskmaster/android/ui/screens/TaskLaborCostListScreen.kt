package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.taskInfoItems.ListItemList

@Composable
fun TaskLaborCostListScreen(navController: NavController){
    Column {
        Header(
            text = "",
            iconItem = 0,
            actionIcons = listOf(),
            navController,
            spacer = false)
        ListItemList()
    }
}