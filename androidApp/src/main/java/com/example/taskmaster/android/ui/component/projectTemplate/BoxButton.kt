package com.example.taskmaster.android.ui.component.projectTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.popupWindows.NewTaskWindow

@Composable
fun BoxButton(text: String, cardContainerFlag: Boolean, onCLick: (() -> Unit)? = null) {
    Button(
        onClick = { onCLick?.invoke() },
        modifier = Modifier
            .padding(bottom = 14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = if(!cardContainerFlag) BorderStroke(1.dp, Color.Black) else BorderStroke(0.dp, Color.Transparent)
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