package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.calculationScreenItems.CalculationOfLaborCosts
import com.example.taskmaster.android.ui.component.commonTemplate.Header

@Composable
fun CalculationOfLaborCostsScreen(navController: NavController, result: Int?, title: String?){
    Column {
        Header(
            text = title ?: "Заголовок отсутствует",
            iconItem = R.drawable.more,
            actionIcons = listOf(
                R.drawable.search1_icon, R.drawable.users_icon
            ),
            navController = navController,
            actionTitle = listOf("Поиск", "Пользователи"),
            projectScreenKey = false,
            projectId = result
        )
        CalculationOfLaborCosts(id = result)
    }
}