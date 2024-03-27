package com.example.taskmaster.android.ui.component.ProjectTemplate

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.R

@Composable
fun BoxButton(text: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = null,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = text, fontSize = 12.sp
        )
    }
}

@Composable
@Preview
fun BoxButtonPreview() {
    BoxButton(text = "Привет")
}