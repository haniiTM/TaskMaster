package com.example.taskmaster.android.ui.component.ProjectTemplate

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculationTimeButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, top = 26.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "Расчет времени по трудозатратам", fontSize = 12.sp
        )
    }
}
