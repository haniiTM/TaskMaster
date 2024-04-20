package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.android.R

@Composable
fun InfoBlockButtonTemplate(categoryText: String, param: Any, avatar: Int = 0) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    var paramItem by remember {
        mutableStateOf(param)
    }
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(0),
        contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
        if (avatar > 0) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { openDialog = true },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
    Divider (
        color = Color.Black,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = !openDialog }) {
            ChangeInfoValueTemplate(title = categoryText, value = param.toString(), placeholder = categoryText, onDismissRequest = { openDialog = !openDialog} )
        }
    }
}