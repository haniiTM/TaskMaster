package com.example.taskmaster.android.ui.component

import android.content.Context
import android.os.Build
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.taskmaster.state.ItemProjectState
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ItemProject(item: ItemProjectState, context: Context, navController: NavController) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val delete = SwipeAction(onSwipe = {
        val vibrationEffect1: VibrationEffect =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
    }, icon = {
        Icon(
            modifier = Modifier.padding(25.dp),
            painter = painterResource(id = R.drawable.delete_icon),
            tint = Color.Black,
            contentDescription = null
        )
    }, background = com.taskmaster.ui.theme.LightRed
    )
    val done = SwipeAction(onSwipe = {
        val vibrationEffect1: VibrationEffect =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
    }, icon = {
        Icon(
            modifier = Modifier.padding(25.dp),
            painter = painterResource(id = R.drawable.done_icon),
            tint = Color.Black,
            contentDescription = null
        )
    }, background = com.taskmaster.ui.theme.Lime
    )
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(25.dp)).clickable { navController.navigate("projectSubTask") }
    ) {
        SwipeableActionsBox(
            endActions = listOf(delete),
            startActions = listOf(done),
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
