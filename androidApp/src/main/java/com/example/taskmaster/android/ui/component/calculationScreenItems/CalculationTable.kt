package com.example.taskmaster.android.ui.component.calculationScreenItems

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.navigation.NavigationItem
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import com.example.taskmaster.android.ui.theme.Lime
import org.koin.androidx.compose.getViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TableHeader(dates: List<Date?>) {
    val uniqueDates = dates.distinct().filterNotNull().sorted()

    Row(Modifier.background(Color.LightGray)) {
        TableHeaderCell(text = "Задача/Дата")
        uniqueDates.forEach { date ->
            TableHeaderCell(text = formatDate(date))
        }
    }
}

@Composable
fun TableRow(
    navController: NavController,
    navId: Int?,
    title: String,
    data: Pair<Int?, String?>? = null,
    ganttData: Pair<Int, Boolean>? = null,
    dates: List<Date?>,
    hoursData: List<Triple<Date?, String?, Int?>>? = null,
    calendarPlan: Boolean,
    hoursGanttData: List<Triple<Date?, String?, Int?>>? = null
) {
    val uniqueDates = dates.distinct().filterNotNull().sorted()

    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    ) {
        FirstTableDataCell(text = if(calendarPlan) ganttData?.first.toString() else data?.first.toString(), navController, navId, title)

        uniqueDates.forEach { date ->
            val matchingEntry = if(calendarPlan) hoursGanttData?.find { it.first == date && it.third == ganttData?.first } else hoursData?.find { it.first == date && it.third == data?.first }
            val matchingHour = if (calendarPlan) "" else matchingEntry?.second ?: "-"
            val matchingHourColor = if (calendarPlan && matchingEntry?.second == "true") Lime else Color.White
            TableDataCell(text = matchingHour, calendarPlan, matchingHourColor)
        }
    }
}

@Composable
fun CalculationTable(
    laborCostViewModel: ManHoursViewModel = getViewModel(),
    ganttViewModel: UserroleprojectViewModel = getViewModel(),
    testViewModel: UserroleprojectViewModel = getViewModel(),
    id: Int?,
    calendarPlan: Boolean,
    navController: NavController,
    title: String?
) {
    Log.d("project id", id.toString())
    LaunchedEffect(key1 = id) {
        laborCostViewModel.getReportManHours(id!!)
        testViewModel.getCalendarPlan(id!!)
        ganttViewModel.getCalendarPlan(id!!)
    }
    val laborCosts = laborCostViewModel.stateManHoursReport.value.itemState
    val dates = laborCosts.map { it?.createdAt?.toDate() }
    val uniqueTaskIds = laborCosts.mapNotNull { it?.taskId }.distinct()
    val labors = uniqueTaskIds.map { taskId ->
        val hoursSpent = laborCosts.firstOrNull { it?.taskId == taskId }?.hoursSpent ?: "-"
        Pair(taskId, hoursSpent)
    }
    val uniqueDates = dates.distinct().filterNotNull().sorted()
    val hoursData = laborCosts.map {
        Triple(it?.createdAt?.toDate(), it?.hoursSpent ?: "-", it?.taskId)
    }
    val gantt = ganttViewModel.state.value.itemState
    val ganttDatesAsString = gantt.filterNotNull().flatMap { it.execution_date }
    val ganttDatesAsDates = ganttDatesAsString.mapNotNull { it?.toGanttDate() }
    val ganttValue = gantt.map {
        Pair(it?.taskId ?: -1, it?.haveExecuter ?: false)
    }.sortedBy { it.first }.distinct()
    Log.d("ganttValue", ganttValue.toString())
    val hoursGanttData = gantt.flatMap { task ->
        task?.execution_date?.map { date ->
            Triple(date?.toGanttDate(), task.haveExecuter?.toString() ?: "-", task.taskId)
        } ?: emptyList()
    }
    val uniqueGanttDates = ganttDatesAsDates.distinct().filterNotNull().sorted()
    Box(
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, top = 26.dp, bottom = 13.dp)
            .height(200.dp)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(15.dp))
    ) {
        if (calendarPlan) {
            LazyRow(
                Modifier
                    .fillMaxSize()
                    .clip(
                        shape = if (uniqueGanttDates.size >= 3) RoundedCornerShape(15.dp) else RoundedCornerShape(
                            15.dp,
                            0.dp,
                            0.dp,
                            15.dp
                        )
                    ),
            ) {
                item {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .clip(
                                shape = if (uniqueGanttDates.size >= 3) RoundedCornerShape(15.dp) else RoundedCornerShape(
                                    15.dp,
                                    0.dp,
                                    0.dp,
                                    15.dp
                                )
                            )
                    ) {
                        item { TableHeader(ganttDatesAsDates) }
                        itemsIndexed(ganttValue) { _, rowData ->
                            TableRow(ganttData = rowData, dates = ganttDatesAsDates, hoursGanttData = hoursGanttData, calendarPlan = calendarPlan, navController = navController, navId = rowData.first, title = title ?: "Заголовок отсутствует")
                        }
                    }
                }
            }
        } else {
            LazyRow(
                Modifier
                    .fillMaxSize()
                    .clip(
                        shape = if (uniqueDates.size >= 3) RoundedCornerShape(15.dp) else RoundedCornerShape(
                            15.dp,
                            0.dp,
                            0.dp,
                            15.dp
                        )
                    ),
            ) {
                item {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .clip(
                                shape = if (uniqueDates.size >= 3) RoundedCornerShape(15.dp) else RoundedCornerShape(
                                    15.dp,
                                    0.dp,
                                    0.dp,
                                    15.dp
                                )
                            )
                    ) {
                        item { TableHeader(dates) }
                        itemsIndexed(labors) { _, rowData ->
                            TableRow(data = rowData, dates = dates, hoursData = hoursData, calendarPlan = calendarPlan, navController = navController, navId = rowData.first, title = title ?: "Заголовок отсутствует")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TableHeaderCell(
    text: String
) {
    Box(
        modifier = Modifier
            .sizeIn(minHeight = 65.dp)
            .width(90.dp)
            .border(BorderStroke(1.dp, Color.Black)), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, Modifier.padding(8.dp), color = Color.Black
        )
    }
}

@Composable
fun FirstTableDataCell(
    text: String,
    navController: NavController,
    navId: Int?,
    title: String
) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(40.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color.LightGray)
            .clickable {
                navController.navigate(
                    NavigationItem.TaskInfo.passIdAndTitle(
                        navId!!.toInt(),
                        title
                    )
                )
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, Modifier.padding(8.dp), color = Color.Black
        )
    }
}

@Composable
fun TableDataCell(
    text: String,
    calendarPlan: Boolean,
    backgroundColor: Color
) {
    if (calendarPlan){
        Box(
            modifier = Modifier
                .border(1.dp, Color.Black)
                .width(90.dp)
                .height(40.dp)
                .background(backgroundColor)
        ) {}
    } else {
        Text(
            text = text,
            Modifier
                .border(1.dp, Color.Black)
                .width(90.dp)
                .height(40.dp)
                .padding(8.dp),
            textAlign = TextAlign.Center, color = Color.Black
        )
    }
}

fun String.toDate(): Date? {
    if (this == null || this == "null") {
        return null
    }
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return sdf.parse(this)
}

fun formatDate(date: Date): String {
    val sdf = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
    return sdf.format(date)
}

fun String.toGanttDate(): Date? {
    if (this == null || this == "null") {
        return null
    }
    val format = "yyyy-MM-dd"
    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
    try {
        return sdf.parse(this)
    } catch (e: ParseException) {
        // ignore
    }
    return null
}