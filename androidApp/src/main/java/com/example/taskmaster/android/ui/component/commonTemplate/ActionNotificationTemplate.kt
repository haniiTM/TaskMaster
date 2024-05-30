package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ActionNotificationTemplate(
    buttonDisplay: Boolean = true,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
    viewModel: TaskViewModel = getViewModel(),
    id: Int? = 0,
    parent: Int? = 0,
    projectKey: Boolean? = false,
    text : String = "Вы уверены?"
) {
    val gradient = Brush.verticalGradient(
        0f to MaterialTheme.colorScheme.secondary,
        1f to MaterialTheme.colorScheme.surfaceTint
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                shape = RoundedCornerShape(25.dp)
            )
            .background(gradient)
            .defaultMinSize(minHeight = 128.dp, minWidth = 292.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 11.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 14.dp),
                color = MaterialTheme.colorScheme.error
            )
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.padding(bottom = 19.dp),
                textAlign = TextAlign.Center
            )
            if(buttonDisplay){
                Row(
                    modifier = Modifier
                        .padding(horizontal = 44.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (projectKey == true) {
                                if(id != null) {
                                    onConfirmation()
                                    viewModel.deleteTaskOrProject(id!!, parent!!, true)
                                }
                            } else {
                                onConfirmation()
                                viewModel.deleteTaskOrProject(id!!, parent!!)
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.defaultMinSize(minWidth = 72.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.White
                        )
                    ) {
                        Text(text = "Да", color = Color.Black, fontWeight = FontWeight.Normal)
                    }
                    Button(
                        onClick = { onDismissRequest() },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.defaultMinSize(minWidth = 72.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.White
                        )
                    ) {
                        Text(text = "Нет", color = Color.Black, fontWeight = FontWeight.Normal)
                    }
                }
            }
        }
    }
}