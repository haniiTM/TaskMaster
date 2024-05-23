package com.example.taskmaster.android.ui.component.projectTemplate

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.ActionNotificationTemplate
import com.example.taskmaster.android.ui.navigation.NavigationItem
import com.example.taskmaster.domain.models.ItemProjectState
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ProjectCard(item: ItemProjectState, navController: NavController) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
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
    if ( showDialog ){
        Dialog(onDismissRequest = { showDialog = !showDialog }) {
            ActionNotificationTemplate(
                onConfirmation = { showDialog = !showDialog },
                onDismissRequest = { showDialog = !showDialog },
                title = "Удаление проекта",
                id = item.id.toInt(),
                projectKey = true
            )
        }
    }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), shape = RoundedCornerShape(25.dp))
            .clickable { navController.navigate(NavigationItem.ProjectTask.passIdAndTitle(id = item.id.toInt(), title = item.projectTitle, item.id.toInt())) }
    ) {
        SwipeableActionsBox(
            endActions = listOf(delete),
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
                            text = item.projectTitle,
                            modifier = Modifier.width(226.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Время на выполнение: " + item.projectTimeLeft.toString(),
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Участники: " + item.projectMemberCount.toString(),
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

