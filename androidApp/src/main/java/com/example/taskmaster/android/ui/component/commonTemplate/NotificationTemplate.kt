package com.example.taskmaster.android.ui.component.commonTemplate

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.navigation.NavigationItem
import com.example.taskmaster.data.network.models.Notification

fun taskList(ids: List<Int?>, titles: List<String?>, tasksNames: List<String?>) {
    val tasks = Triple(ids, titles, tasksNames)
    val abc = tasks.first
    Log.d("tasks", tasks.toString())
    Log.d("abc", abc.toString())
}

@Composable
fun NotificationTemplate(
    notificationTaskList: MutableList<Notification?>,
    navController: NavController
) {
    val gradient = Brush.verticalGradient(
        0f to MaterialTheme.colorScheme.secondary,
        1f to MaterialTheme.colorScheme.surfaceTint
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(25.dp))
            .background(gradient)
            .width(292.dp)
            .height(356.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 11.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Уведомление",
                modifier = Modifier.padding(bottom = 25.dp),
                color = Color.Red
            )
            Text(
                text = stringResource(id = R.string.notification_subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.padding(bottom = 15.dp, start = 17.dp, end = 17.dp)
            )
            Text(
                text = stringResource(id = R.string.notification_text),
                textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.padding(bottom = 25.dp, start = 17.dp, end = 17.dp)
            )
            LazyColumn {
                notificationTaskList.forEach { notification ->
                    notification?.let {
                        itemsIndexed(notification.listTask) { index, task ->
                            Text(
                                text = task.taskName ?: "",
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.padding(bottom = 5.dp).clickable {
                                    navController.navigate(
                                        NavigationItem.TaskInfo.passIdAndTitle(
                                            id = task.taskId ?: -1,
                                            title = it.projectName ?: ""
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
