package com.example.taskmaster.android.ui.component.TaskInfoItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LaborCostItem(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp).border(BorderStroke(1.dp, Color.Black), shape = RectangleShape), contentAlignment = Alignment.CenterStart
    ) {
        Text(text = "Трудозатрата 1", modifier = Modifier.padding(horizontal = 12.dp))
    }
}

@Preview
@Composable
fun lcip(){
    LaborCostItem()
}