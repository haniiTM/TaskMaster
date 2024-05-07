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
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.android.ui.screens.type_of_activity.TypeOfActivityViewModel
import com.example.taskmaster.android.ui.theme.PlaceHolder
import com.example.taskmaster.data.network.models.TaskDTO
import org.koin.androidx.compose.getViewModel

@Composable
fun NewTaskWindow(
    viewModel: TypeOfActivityViewModel = getViewModel(),
    viewModelTask: TaskViewModel = getViewModel(),
    id: Int,
    onDismissRequest: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.getTypeActivity()
    }
    val typeActivity = viewModel.state.value.itemState
    var taskTitle by remember {
        mutableStateOf("")
    }
    var taskDependence by remember {
        mutableStateOf("")
    }
    var taskAllocatedTime by remember {
        mutableStateOf("")
    }
    var taskCategory by remember {
        mutableStateOf("")
    }

    var categoryId by remember { mutableStateOf(0) }
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
                    .height(195.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(35.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Новая задача",
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                UnifiedTextBox(
                    value = taskTitle,
                    onValueChange = { newValue -> taskTitle = newValue },
                    placeholder = "Название задачи"
                )
                UnifiedTextBox(
                    value = taskAllocatedTime,
                    onValueChange = { newValue -> taskAllocatedTime = newValue },
                    prefix = { Text(text = "Временная оценка: ", color = Color.Black) },
                    timeUnifiedTextFieldKey = true
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
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (taskCategory == "") {
                                Text(
                                    text = "Выбор категории",
                                    color = PlaceHolder,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_circle_right_icon),
                                    contentDescription = "",
                                    modifier = Modifier.rotate(90f),
                                    tint = Color.Black
                                )
                            } else {
                                Text(
                                    text = "Категория:",
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    text = taskCategory,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            DropdownMenu(
                                expanded = categoryExpanded,
                                onDismissRequest = { categoryExpanded = !categoryExpanded },
                                modifier = Modifier
                                    .fillMaxWidth(.61f)
                                    .height(185.dp)
                                    .background(Color.White)
                            ) {
                                typeActivity.forEach { item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            categoryExpanded = false
                                            taskCategory = item!!.name
                                            categoryId = item!!.id
                                        },
                                        text = {
                                            if (item != null) {
                                                Text(
                                                    text = item.name,
                                                    fontWeight = FontWeight.Normal
                                                )
                                            }
                                        },
                                        colors = MenuDefaults.itemColors(textColor = Color.Black)
                                    )
                                }

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
                    onClick = {
//                        val task = TaskDTO() {
//                            name = taskTitle,
//                            scope = taskAllocatedTime,
//                            typeActivity = categoryId
//                        }
                        val task = TaskDTO()
                        task.name = taskTitle
                        task.scope = taskAllocatedTime.toInt()
                        task.typeofactivityid = categoryId
                        viewModelTask.createTask(task, id)
                        onDismissRequest()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
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