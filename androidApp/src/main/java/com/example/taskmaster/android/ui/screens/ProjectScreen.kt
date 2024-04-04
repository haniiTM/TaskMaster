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
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ProjectScreen(navController: NavController, viewModel: TaskViewModel = getViewModel()) {
    viewModel.getProject()
   val projects = viewModel.state.value.itemProjectState
    LazyColumn(modifier = Modifier.padding(horizontal = 14.dp)) {
        item { Spacer(modifier = Modifier.padding(top = 26.dp)) }
        itemsIndexed(projects) { _, item ->
            if (item != null) {
                ProjectCard(item = item , navController = navController)
            }
        }
    }
}