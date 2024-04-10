package com.example.taskmaster.android.ui.component.popupWindows

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLaborCostWindow(onDismissRequest: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    var date by remember {
        mutableStateOf("")
    }
    var comment by remember {
        mutableStateOf("")
    }
    var spendedTime by remember {
        mutableStateOf("")
    }
    val laborCostCategoryList =
        listOf("Проектирование", "Разработка", "Дизайн", "Расследование", "Дизайн")
    var laborCostCategory by remember {
        mutableStateOf("")
    }
    val linearGradient =
        Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onSecondary
            )
        )
    var categoryExpanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 38.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(35.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Новая трудозатрата",
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                BasicTextField(
                    value = "Дата: $date",
                    onValueChange = { date = it },
                    modifier = Modifier
                        .background(color = Color.White)
                        .height(35.dp)
                        .fillMaxWidth(),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    decorationBox = @Composable { innerTextField ->
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = date,
                            innerTextField = innerTextField,
                            enabled = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White
                            ),
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.calendar_icon),
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            },
                            singleLine = true,
                            contentPadding = PaddingValues(start = 10.dp),
                            visualTransformation = VisualTransformation.None,
                            interactionSource = interactionSource)
                    }
                )
                BasicTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    modifier = Modifier
                        .background(color = Color.White)
                        .height(35.dp)
                        .fillMaxWidth(),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    decorationBox = @Composable { innerTextField ->
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = comment,
                            innerTextField = innerTextField,
                            enabled = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White
                            ),
                            singleLine = true,
                            contentPadding = PaddingValues(horizontal = 10.dp),
                            visualTransformation = VisualTransformation.None,
                            interactionSource = interactionSource,
                            placeholder = { Text("Комментарий") })
                    }
                )
                BasicTextField(
                    value = "Затрачено: $spendedTime",
                    onValueChange = { spendedTime = it },
                    modifier = Modifier
                        .background(color = Color.White)
                        .height(35.dp)
                        .fillMaxWidth(),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    decorationBox = @Composable { innerTextField ->
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = spendedTime,
                            innerTextField = innerTextField,
                            enabled = true,
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.clock_icon),
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White
                            ),
                            singleLine = true,
                            contentPadding = PaddingValues(start = 10.dp),
                            visualTransformation = VisualTransformation.None,
                            interactionSource = interactionSource)
                    }
                )
                Button(
                    onClick = { categoryExpanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize().padding(end = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (laborCostCategory == "") {
                                Text(
                                    text = "Выбор деятельности",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_circle_right_icon),
                                    contentDescription = "",
                                    modifier = Modifier.rotate(90f),
                                    tint = Color.Black,
                                )
                            } else {
                                Text(
                                    text = "Деятельность:",
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    text = laborCostCategory,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                        DropdownMenu(
                            expanded = categoryExpanded,
                            onDismissRequest = { categoryExpanded = !categoryExpanded },
                            modifier = Modifier
                                .fillMaxWidth(.61f)
                                .height(185.dp)
                                .background(Color.White)
                        ) {
                            laborCostCategoryList.forEach { item ->
                                DropdownMenuItem(
                                    onClick = {
                                        categoryExpanded = false
                                        laborCostCategory = item
                                    },
                                    text = {
                                        Text(
                                            text = item,
                                            fontWeight = FontWeight.Normal
                                        )
                                    },
                                    colors = MenuDefaults.itemColors(textColor = Color.Black)
                                )
                            }
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
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Text(text = "Создать", color = Color.Black)
                }
            }
        }
    }
}