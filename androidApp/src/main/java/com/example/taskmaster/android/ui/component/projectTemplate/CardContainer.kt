package com.example.taskmaster.android.ui.component.projectTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CardContainer(
    title: String,
    buttonTitle: String,
    navController: NavController,
    id: Int?,
    viewModel: TaskViewModel = getViewModel()
) {
    viewModel.getTask(id!!.toInt()) // Нужно вставить id проекта

    val tasks = viewModel.stateTask.value.itemTaskState
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp, start = 14.dp, end = 14.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), shape = RoundedCornerShape(25.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                modifier = Modifier.padding(top = 14.dp, bottom = 25.dp),
                fontSize = 18.sp,
                color = Color.Black
            )
            LazyColumn(
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, bottom = 14.dp)
                    .clip(shape = RoundedCornerShape(25.dp))
                    .heightIn(min = 232.dp, max = 345.dp)
            ) {
                itemsIndexed(tasks){ _, item ->
                    if (item != null) {
                        ItemProject(
                            item = item,
                            context = LocalContext.current,
                            navController = navController
                        )
                    }
                }
            }
            if (buttonTitle != "") {
                BoxButton(buttonTitle)
            }
        }

    }
}