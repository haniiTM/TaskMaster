package com.example.taskmaster.android.ui.component.projectTemplate

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.ActionNotificationTemplate
import com.example.taskmaster.android.ui.navigation.NavigationItem
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.data.network.models.TaskDTO
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import org.koin.androidx.compose.getViewModel

@Composable
fun ItemProject(item: TaskDTO, context: Context, navController: NavController, viewModel: TaskViewModel = getViewModel(), completed: Boolean = false, projectTitle: String) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    val taskCompleted: SwipeAction
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val delete = SwipeAction(onSwipe = {
        val vibrationEffect1: VibrationEffect =
            VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        showDialog = true
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
    }, icon = {
        Icon(
            modifier = Modifier.padding(25.dp),
            painter = painterResource(id = R.drawable.delete_icon),
            tint = Color.Black,
            contentDescription = null
        )
    }, background = MaterialTheme.colorScheme.surface
    )
    taskCompleted = SwipeAction(onSwipe = {
        val vibrationEffect1: VibrationEffect =
            VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
        val newStatus = if (item.status == 2) 1 else 2
        viewModel.updateStatus(item.id!!, newStatus, item.name!!, item.parent!!)
    }, icon = {
        val iconResId = if (completed) R.drawable.cancel_icon else R.drawable.done_icon
        Icon(
            modifier = Modifier.padding(25.dp),
            painter = painterResource(id = iconResId),
            tint = Color.Black,
            contentDescription = null
        )
    }, background = if (completed) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant
    )
    if ( showDialog ){
        Dialog(onDismissRequest = { showDialog = !showDialog }) {
            ActionNotificationTemplate(
                onConfirmation = { showDialog = !showDialog },
                onDismissRequest = { showDialog = !showDialog },
                title = "Удаление задачи",
                id = item.id!!,
                parent = item.parent!!
            )
        }
    }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 7.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(25.dp))
            .clickable { navController.navigate(NavigationItem.ProjectSubTask.passIdAndTitle(id = item.id!!.toInt(), projectTitle, item.name!!, item.content ?: "Описание отсутствует")) }
    ) {
        SwipeableActionsBox(
            endActions = listOf(delete),
            startActions = listOf(taskCompleted),
            swipeThreshold = 50.dp
        ) {
            Box {
                Row(
                    Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = item.name!!,
                            modifier = Modifier.width(226.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Время на выполнение: " + item.scope.toString(),
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Участники: " + item.userCount.toString(),
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
