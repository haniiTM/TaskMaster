package com.example.taskmaster.android.ui.component.popupWindows

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox

@Composable
fun ChangeInfoValueWindow() {
    var value by remember {
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 38.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                UnifiedTextBox(
                    value = value,
                    onValueChange = { newValue -> value = newValue },
                    placeholder = "Количество часов в день",
                    passwordVisibleValue = true,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardType = KeyboardType.Email,
                    roundedAngle = 0,
                    spacer = 0,
                    borderWidth = 0,
                    icon = R.drawable.clock_icon,
                    changeIcon = R.drawable.clock_icon,
                    prefix = {}
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Text(text = "Сохранить", color = Color.Black)
                }
            }
        }
    }
}