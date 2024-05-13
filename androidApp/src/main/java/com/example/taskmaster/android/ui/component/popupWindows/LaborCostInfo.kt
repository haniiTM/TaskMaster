package com.example.taskmaster.android.ui.component.popupWindows

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taskmaster.data.network.models.ManHoursDTO

@Composable
fun LaborCostInfo( number : String, item : ManHoursDTO) {
    val linearGradient =
        Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onSecondary
            )
        )
    var comment by remember {
        mutableStateOf(item.comment)
    }
    Box(
        modifier = Modifier
            .width(332.dp)
            .height(198.dp)
            .clip(shape = RoundedCornerShape(15.dp)).border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15.dp))
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .background(linearGradient)
            ) {
                Text(text = "Трудозатрата № ${item.id}" , color = MaterialTheme.colorScheme.onTertiary)
            }
            Divider(color = Color.Black)
            TextField(value = comment!!,
                onValueChange = { newValue -> comment = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(0.dp),
                placeholder = {
                    Text(
                        text = "Комментарий"
                    )
                })
            Divider(color = Color.Black)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = "${item.created_at}",
                    color = Color.Black,
                    modifier = Modifier.weight(.5f),
                    textAlign = TextAlign.Center
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp), color = Color.Black
                )
                Text(
                    text = "${item.hours_spent}",
                    color = Color.Black,
                    modifier = Modifier
                        .weight(.5f),
                    textAlign = TextAlign.Center
                )
            }
            Divider(color = Color.Black)
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)
            ) {
                Text(text = "Сохранить", color = Color.Black)
            }
        }
    }
}