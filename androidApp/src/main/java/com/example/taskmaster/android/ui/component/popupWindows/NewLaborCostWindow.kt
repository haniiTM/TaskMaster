package com.example.taskmaster.android.ui.component.popupWindows

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.screens.activity_screen.ActivityViewModel
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.data.network.models.ActivityDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLaborCostWindow(
    onDismissRequest: () -> Unit,
    viewModelActivity: ActivityViewModel = getViewModel(),
    viewModel: ManHoursViewModel = getViewModel(),
    viewTaskModel: TaskViewModel = getViewModel(),
    taskId: Int,
    triggerRefresh: (Boolean) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        viewModelActivity.getActivity()
    }
    var comment by remember {
        mutableStateOf("")
    }
    var spendTime by remember { mutableStateOf("0000") }
    val laborCostCategoryList = viewModelActivity.state.value.itemState

    var laborCostCategory by remember {
        mutableStateOf(ActivityDTO())
    }
    val linearGradient =
        Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onSecondary
            )
        )
    var categoryExpanded by remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat("dd/M/yyyy")
    val currentDateAndTime = dateFormat.format(Date())

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    var mDate by remember { mutableStateOf(currentDateAndTime) }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                        )
                ) {
                    Row(modifier = Modifier.padding(start = 10.dp)){
                        Text(text = "Дата: ", color = Color.Black)
                        Text(text = mDate, color = Color.Black)
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.calendar_icon),
                        contentDescription = "calendar_icon",
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .clickable { mDatePickerDialog.show() },
                        tint = Color.Black
                    )
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
                    prefix = { Text(text = "Затрачено: ", color = Color.Black) },
                    timeUnifiedTextFieldKey = true,
                    keyboardType = KeyboardType.Number
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
                            if (laborCostCategory.name == null) {
                                Text(
                                    text = "Выбор деятельности",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_circle_right_icon),
                                    contentDescription = "arrow_circle_right_icon",
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
                                    text = laborCostCategory.name!!,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.width(IntrinsicSize.Max).fillMaxWidth(0.5f),
                                    overflow = TextOverflow.Ellipsis
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
                                        if (item != null) {
                                            laborCostCategory = item
                                        }
                                    },
                                    text = {
                                        item?.name?.let {
                                            Text(
                                                text = it,
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
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        viewModel.createManHours(
                            ManHoursDTO(
                                comment = comment,
                                hours_spent = spendTime,
                                activityid = laborCostCategory.id ?: 1,
                                taskid = taskId,
                            ),
                            taskId
                        ) { success ->
                            // Вызов коллбэка после обновления данных
                            if (triggerRefresh != null && success) {
                                viewTaskModel.dataTaskById(taskId!!)
                                triggerRefresh(success)
                            }
                        }
                        onDismissRequest()
                    },
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