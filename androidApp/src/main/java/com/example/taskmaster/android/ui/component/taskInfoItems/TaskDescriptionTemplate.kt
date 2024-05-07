package com.example.taskmaster.android.ui.component.taskInfoItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskDescription(description: String?, viewModel: TaskViewModel = getViewModel(), taskId: Int?, onValueChange: (String) -> Unit,
) {
    var descriptionTask by remember { mutableStateOf(description ?: "Описание отсутствует") }
    Column(
        modifier = Modifier
            .padding(start = 14.dp, top = 6.dp, end = 14.dp)
            .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
            .background(Color.White)
    ) {
        TextField(
            value = descriptionTask,
            onValueChange = {newValue -> descriptionTask = newValue},
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .border(
                    BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)
                ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = LocalTextStyle.current.copy(Color.Black)
        )
        Button(
            onClick = { viewModel.updateDescription(
                taskId = taskId ?: 0,
                description =  if(descriptionTask.length == 0) {
                    "Описание отсутствует"
                } else {
                    descriptionTask
                }
                ) },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 25.dp)
                )
                .height(42.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 25.dp)
        ) {
            Text(text = "Сохранить", color = Color.Black)
        }
    }
}