package com.example.taskmaster.android.ui.component.popupWindows

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.theme.ShadowGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLaborCostWindow(onDismissRequest: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    var date by remember {
        mutableStateOf("")
    }
    var comment by remember {
        mutableStateOf("")
    }
    var spendTime by remember {
        mutableStateOf("")
    }
    val laborCostCategoryList =
        listOf("Проектирование", "Разработка", "Дизайн", "Расследование", "Обсуждение")
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
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val calendarState = rememberDatePickerState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 38.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(40.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Новая трудозатрата",
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                Button(onClick = { showDatePickerDialog = !showDatePickerDialog }) {

                }
                if(showDatePickerDialog){
                    DatePickerDialog(
                        onDismissRequest = {
                            showDatePickerDialog = !showDatePickerDialog
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                date = "$calendarState"
                                showDatePickerDialog = !showDatePickerDialog
                            }) {
                                Text(text = "Готово")
                            }
                        }, modifier = Modifier.height(screenHeight/1.5f),
                        colors = DatePickerDefaults.colors(
                            containerColor = Color.White
                        )
                    ) {
                        DatePicker(
                            state = calendarState, colors = DatePickerDefaults.colors(
                                containerColor = Color.White,
                                titleContentColor = Color.Black,
                                headlineContentColor = Color.Black,
                                weekdayContentColor = Color.Black,
                                dayContentColor = Color.Black,
                                subheadContentColor = Color.Black,
                                yearContentColor = Color.Black,
                                currentYearContentColor = Color.Black,
                                selectedYearContentColor = Color.White,
                                selectedYearContainerColor = ShadowGray,
                                selectedDayContentColor = Color.Black,
                                disabledSelectedDayContentColor = Color.Black,
                                selectedDayContainerColor = ShadowGray,
                                disabledSelectedDayContainerColor = Color.Black,
                                todayContentColor = Color.Black,
                                todayDateBorderColor = Color.Black,
                                dayInSelectionRangeContentColor = Color.Black,
                                dayInSelectionRangeContainerColor = Color.Black
                            )
                        )
                    }
                }
                UnifiedTextBox(
                    value = comment,
                    onValueChange = { newValue -> comment = newValue },
                    placeholder = "Комментарий"
                )
                UnifiedTextBox(
                    value = spendTime,
                    onValueChange = { newValue -> spendTime = newValue },
                    icon = R.drawable.clock_icon,
                    changeIcon = R.drawable.clock_icon,
                    prefix = { Text(text = "Затрачено: ", color = Color.Black) }
                )
                Button(
                    onClick = { categoryExpanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 5.dp),
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
                        .height(40.dp),
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