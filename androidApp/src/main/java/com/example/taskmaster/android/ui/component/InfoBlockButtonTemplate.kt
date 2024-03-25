package com.example.taskmaster.android.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoBlockButtonTemplate(categoryText: String, param: Any, avatar: Int = -1) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    val state = rememberTimePickerState()
    var paramItem by remember {
        mutableStateOf(param)
    }
    Button(
        onClick = { openDialog = true },
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(0),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (avatar > 0) {
                Text(
                    text = "$categoryText: $paramItem",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .clip(
                            CircleShape
                        )
                )
            } else {
                Text(
                    text = "$categoryText:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
                Text(
                    text = paramItem.toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
    }
    if (openDialog){
        Dialog(onDismissRequest = { openDialog = !openDialog }) {
            TextField(
                value = paramItem.toString(),
                onValueChange = { paramItem = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .border(
                        BorderStroke(width = 1.dp, color = Color.Black),
                        shape = RoundedCornerShape(25.dp)
                    )
                    .clip(shape = RoundedCornerShape(25.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent
                ),
                textStyle = LocalTextStyle.current.copy(Color.Black),
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun InfoBlockButtonTemplatePreview() {
    InfoBlockButtonTemplate(categoryText = "Участники", param = "24:0")

}