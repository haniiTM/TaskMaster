package com.example.taskmaster.android.ui.component.taskInfoItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.InfoBlockButtonTemplate
import com.example.taskmaster.android.ui.component.popupWindows.NewLaborCostWindow
import com.example.taskmaster.android.ui.navigation.NavigationItem
import com.example.taskmaster.android.ui.screens.status_screen.StatusViewModel
import com.example.taskmaster.android.ui.screens.type_of_activity.TypeOfActivityViewModel
import org.koin.androidx.compose.getViewModel
@Composable
fun TaskInfoBlock(
    navController: NavController,
    viewModel: StatusViewModel = getViewModel(),
    viewModelTypeOfActivity: TypeOfActivityViewModel = getViewModel(),
    name: String,
    spentTime: Int,
    spentedTime: String,
    scope: Int,
    status: Int,
    userCount: Int,
    typeofactivityid: Int,
    id: Int?,
    title: String?) {

    LaunchedEffect(key1 = true) {
        viewModel.getStatus()
        viewModelTypeOfActivity.getTypeActivity()
    }

    var taskCategory by remember {
        mutableStateOf("")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    val linearGradient =
        Brush.verticalGradient(listOf(MaterialTheme.colorScheme.onPrimary, MaterialTheme.colorScheme.onSecondary))
    var statusExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 38.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(264.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(32.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                )
                InfoBlockButtonTemplate(
                    categoryText = "Участники",
                    param = userCount,
                    avatar = R.drawable.logo
                )
                InfoBlockButtonTemplate(
                    categoryText = "Затрачиваемые часы / день",
                    param = spentTime
                )
                InfoBlockButtonTemplate(categoryText = "Оценка времени", param = scope)
                InfoBlockButtonTemplate(categoryText = "Затрачено времени", param = spentedTime)
                Button(
                    onClick = { categoryExpanded = true },
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
                                text = "Категория:",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = viewModelTypeOfActivity.state.value.itemState.find { typeOfActivity ->
                                    typeOfActivity?.id == typeofactivityid
                                }?.name ?: "",
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
                                expanded = categoryExpanded,
                                onDismissRequest = { categoryExpanded = !categoryExpanded },
                                modifier = Modifier
                                    .fillMaxWidth(.61f)
                                    .height(185.dp)
                                    .background(Color.White)
                                    .height(185.dp)
                            ) {
                                viewModelTypeOfActivity.state.value.itemState.forEach { item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            categoryExpanded = false
                                            taskCategory = item?.name ?: ""
                                        },
                                        text = {
                                            Text(
                                                text = item?.name ?: "",
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Black
                                            )
                                        },
                                        colors = MenuDefaults.itemColors(textColor = Color.White)
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
                    onClick = { statusExpanded = true },
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
                                text = viewModel.state.value.itemState.find { item ->
                                    item?.id == status
                                }?.name ?: "",
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
                                expanded = statusExpanded,
                                onDismissRequest = { statusExpanded = !statusExpanded },
                                modifier = Modifier
                                    .fillMaxWidth(.61f)
                                    .height(185.dp)
                                    .background(Color.White)
                            ) {
                                viewModel.state.value.itemState.forEach { item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            statusExpanded = false
                                            if (item != null) {
                                                viewModel.state.value.itemState.find { item ->
                                                    item?.id == status
                                                }?.name!!
                                            }
                                        },
                                        text = {
                                            if (item != null) {
                                                Text(
                                                    text = item.name,
                                                    fontWeight = FontWeight.Normal,
                                                    color = Color.Black
                                                )
                                            }
                                        },
                                        colors = MenuDefaults.itemColors(textColor = Color.White)
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
                    onClick = { navController.navigate(NavigationItem.TaskLaborCostListScreen.passIdAndTitle(id!!.toInt(), title!!)) },
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
            onClick = { showDialog = true },
            modifier = Modifier
                .padding(vertical = 24.dp)
                .width(168.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            contentPadding = PaddingValues(horizontal = 1.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Text(text = "Добавить трудозатраты", color = Color.Black, fontSize = 12.sp)
        }
        if (showDialog){
            Dialog(onDismissRequest = { showDialog = !showDialog}) {
                NewLaborCostWindow(onDismissRequest = {showDialog = !showDialog}, taskId = id!!)
            }
        }
    }
}