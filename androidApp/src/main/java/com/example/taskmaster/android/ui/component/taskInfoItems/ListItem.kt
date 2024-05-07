package com.example.taskmaster.android.ui.component.taskInfoItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(name: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp), contentAlignment = Alignment.CenterStart
    ) {
        Text(text = name, modifier = Modifier.padding(horizontal = 12.dp), color = Color.Black)
    }
    Divider (
        color = MaterialTheme.colorScheme.outline,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
}
