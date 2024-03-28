package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.projectTemplate.ProjectCard
import com.taskmaster.state.ItemProjectState

@Composable
fun ProjectScreen(navController: NavController) {
    LazyColumn(modifier = Modifier
        .padding(horizontal = 14.dp)) {
        item { Spacer(modifier = Modifier.padding(top = 26.dp)) }
        itemsIndexed(
            listOf(
                ItemProjectState("Сайт Nissan", 72, 4),
                ItemProjectState("Мобильное приложение Alabuga Tech", 72, 4),
                ItemProjectState("Мобильное приложение Chudnoi perets", 72, 4),
                ItemProjectState("Сайт Nissan", 72, 4),
                ItemProjectState("Мобильное приложение Alabuga Tech", 72, 4),
                ItemProjectState("Мобильное приложение Chudnoi perets", 72, 4),
                ItemProjectState("Сайт Nissan", 72, 4),
                ItemProjectState("Мобильное приложение Alabuga Tech", 72, 4),
                ItemProjectState("Мобильное приложение Chudnoi perets", 72, 4),
                ItemProjectState("Сайт Nissan", 72, 4),
                ItemProjectState("Мобильное приложение Alabuga Tech", 72, 4),
                ItemProjectState("Мобильное приложение Chudnoi perets", 72, 4),
            )
        ) { _, item ->
            ProjectCard(item = item , navController = navController)
        }
    }
}