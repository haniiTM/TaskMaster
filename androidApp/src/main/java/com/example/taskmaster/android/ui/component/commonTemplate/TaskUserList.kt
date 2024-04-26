package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun TaskUserList(
    checkBoxAble: Boolean,
    addRoleButton: Boolean,
    title: String = "",
    buttonText: String,
    paddingValue: Int = 0,
    onCloseButtonClick: (() -> Unit)? = null

) {
    val list = listOf(
        "Иванов Иван Иванович",
        "Сидоров Петр Сергеевич",
        "Петров Петр Петрович",
        "Сидоров Петр Сергеевич",
        "Сидоров Петр Сергеевич"
    )

    val linearGradient =
        Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onSecondary
            )
        )

    var showWindow by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .padding(horizontal = paddingValue.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15.dp)
            )
            .sizeIn(maxHeight = 250.dp)
    ) {
        Column {
            if (title != "") {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(35.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            LazyColumn(
                state = rememberLazyListState(), modifier = Modifier.sizeIn(maxHeight = 180.dp)
            ) {
                itemsIndexed(list) { _, item ->
                    if (item != null) {
                        UserCard(
                            checkBoxAble = checkBoxAble,
                            addRoleButton = addRoleButton,
                            item = item
                        )
                    }
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if (onCloseButtonClick == null) {
                        showWindow = true
                    } else {
                        onCloseButtonClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Text(text = buttonText, color = Color.Black)
            }
        }
    }
    if (showWindow) {
        Dialog(onDismissRequest = { showWindow = false }) {
            TaskUserList(
                checkBoxAble = true,
                addRoleButton = false,
                title = "выберите пользователя",
                buttonText = "Добавить",
                paddingValue = 0,
                onCloseButtonClick = { showWindow = false }
            )
        }
    }
}