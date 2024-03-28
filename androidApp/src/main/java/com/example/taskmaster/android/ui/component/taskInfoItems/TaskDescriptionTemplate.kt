package com.example.taskmaster.android.ui.component.taskInfoItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDescription(description: String) {
    var descriptionTask by remember { mutableStateOf(description) }
    Column(
        modifier = Modifier
            .padding(start = 14.dp, top = 6.dp, end = 14.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(Color.White)
    ) {
        TextField(
            value = descriptionTask,
            onValueChange = { descriptionTask = it },
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .border(
                    BorderStroke(width = 1.dp, color = Color.Black),
                    shape = RoundedCornerShape(25.dp)
                ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = LocalTextStyle.current.copy(Color.Black)
        )
    }
}