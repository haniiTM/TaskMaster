package com.example.taskmaster.android.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LaborCostList() {
    Box(
        modifier = Modifier
            .padding(start = 14.dp, top = 26.dp, end = 14.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15.dp))
            .background(Color.White)
    ) {
        Column {
            LaborCostItem()
            LaborCostItem()
            LaborCostItem()
        }
    }
}

@Preview
@Composable
fun LaborCostPrev() {
    LaborCostList()
}