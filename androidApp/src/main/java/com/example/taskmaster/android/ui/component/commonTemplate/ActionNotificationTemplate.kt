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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ActionNotificationTemplate(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(25.dp))
            .background(Color.White)
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
                color = Color.Red
            )
            Text(
                text = "Вы уверены?",
                color = Color.Black,
                modifier = Modifier.padding(bottom = 19.dp)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 44.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onConfirmation() },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.defaultMinSize(minWidth = 72.dp)
                ) {
                    Text(text = "Да", color = Color.Black, fontWeight = FontWeight.Normal)
                }
                Button(
                    onClick = { onDismissRequest() },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.defaultMinSize(minWidth = 72.dp)
                ) {
                    Text(text = "Нет", color = Color.Black, fontWeight = FontWeight.Normal)
                }
            }
        }
    }
}