package com.example.taskmaster.android.ui.component.TaskInfoItems

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.CommonTemplate.InfoBlockButtonTemplate

@Composable
fun TaskInfoBlock(navController: NavController) {
    val members by remember {
        mutableStateOf(4)
    }
    val allocatedTime by remember {
        mutableStateOf("4:00")
    }
    val spentPerDay by remember {
        mutableStateOf("1:00")
    }
    val wastedTime by remember {
        mutableStateOf("3:29")
    }
    var taskStatus by remember {
        mutableStateOf("Новая")
    }
    var taskStatusList =
        listOf("В работе", "Заблокирована", "Решена", "Нужен отклик", "Закрыта", "Отклонена")
    val taskTitle by remember {
        mutableStateOf("Изучение семантики языка")
    }
    val linearGradient =
        Brush.verticalGradient(listOf(Color.White, com.taskmaster.ui.theme.Crayola))
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 38.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(224.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(32.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = taskTitle,
                        color = Color.Black,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                InfoBlockButtonTemplate(
                    categoryText = "Участники",
                    param = members,
                    avatar = R.drawable.logo
                )
                InfoBlockButtonTemplate(
                    categoryText = "Затрачиваемые часы / день",
                    param = spentPerDay
                )
                InfoBlockButtonTemplate(categoryText = "Оценка времени", param = allocatedTime)
                InfoBlockButtonTemplate(categoryText = "Затрачено времени", param = wastedTime)
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Статус задачи:",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = taskStatus,
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )

                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = !expanded },
                                modifier = Modifier
                                    .fillMaxWidth(.67f)
                                    .height(185.dp)
                            ) {
                                taskStatusList.forEach { item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            expanded = false
                                            taskStatus = item
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
                }

                Button(
                    onClick = { navController.navigate("taskLaborCostList") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Text(text = "Трудозатраты", color = Color.Black)
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(vertical = 24.dp)
                .width(168.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            contentPadding = PaddingValues(horizontal = 1.dp)
        ) {
            Text(text = "Добавить трудозатраты", color = Color.Black, fontSize = 12.sp)
        }
    }
}