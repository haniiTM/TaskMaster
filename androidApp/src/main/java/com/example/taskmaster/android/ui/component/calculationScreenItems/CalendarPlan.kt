//package com.example.taskmaster.android.ui.component.calculationScreenItems
//
//import android.util.Log
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.sizeIn
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
//import com.example.taskmaster.android.ui.theme.Lime
//import org.koin.androidx.compose.getViewModel
//import java.text.ParseException
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//@Composable
//fun GanttTableHeader(dates: List<Date?>) {
//    val uniqueDates = dates.distinct().filterNotNull().sorted()
//
//    Row(Modifier.background(Color.LightGray)) {
//        GanttTableHeaderCell(text = "Задача/Дата")
//        uniqueDates.forEach { date ->
//            GanttTableHeaderCell(text = GanttFormatDate(date))
//        }
//    }
//}
//
//@Composable
//fun GanttTableRow(
//    data: Pair<Int, Boolean>,
//    dates: List<Date?>,
//    hoursData: List<Triple<Date?, String?, Int?>>
//) {
//    val uniqueDates = dates.distinct().filterNotNull().sorted()
//    Log.d("uniqueDates", uniqueDates.toString())
//    Row(
//        Modifier
//            .fillMaxWidth()
//            .height(40.dp)
//            .background(Color.White)
//    ) {
//        GanttFirstTableDataCell(text = data.first.toString())
//
//        uniqueDates.forEach { date ->
//            val matchingEntry = hoursData.find { it.first == date && it.third == data.first }
//            val color = if (matchingEntry?.second == "true") Lime else Color.White
//            GanttTableDataCell(backgroundColor = color)
//        }
//    }
//}
//
//@Composable
//fun CalendarPlan(ganttViewModel: UserroleprojectViewModel = getViewModel(), id: Int?) {
//    Log.d("project id", id.toString())
//    LaunchedEffect(key1 = id) {
//        ganttViewModel.getCalendarPlan(id!!)
//    }
//    val gantt = ganttViewModel.state.value.itemState
//    val ganttDatesAsString = gantt.filterNotNull().flatMap { it.execution_date }
//    val ganttDatesAsDates = ganttDatesAsString.mapNotNull { it?.toGanttDate() }
//    val ganttValue = gantt.map {
//        Pair(it?.taskId ?: -1, it?.haveExecuter ?: false)
//    }.sortedBy { it.first }.distinct()
//    Log.d("ganttValue", ganttValue.toString())
//    val hoursGanttData = gantt.flatMap { task ->
//        task?.execution_date?.map { date ->
//            Triple(date?.toGanttDate(), task.haveExecuter?.toString() ?: "-", task.taskId)
//        } ?: emptyList()
//    }
//    Log.d("hoursGanttData", hoursGanttData.toString())
//    val uniqueGanttDates = ganttDatesAsDates.distinct().filterNotNull().sorted()
//
//    Box(
//        modifier = Modifier
//            .padding(start = 14.dp, end = 14.dp, top = 26.dp, bottom = 13.dp,)
//            .height(200.dp)
//            .fillMaxWidth()
//            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(15.dp))
//    ) {
//        LazyRow(
//            Modifier
//                .fillMaxSize()
//                .clip(
//                    shape = if (uniqueGanttDates.size >= 3) RoundedCornerShape(15.dp) else RoundedCornerShape(
//                        15.dp,
//                        0.dp,
//                        0.dp,
//                        15.dp
//                    )
//                ),
//        ) {
//            item {
//                LazyColumn(
//                    Modifier
//                        .fillMaxSize()
//                        .clip(
//                            shape = if (uniqueGanttDates.size >= 3) RoundedCornerShape(15.dp) else RoundedCornerShape(
//                                15.dp,
//                                0.dp,
//                                0.dp,
//                                15.dp
//                            )
//                        )
//                ) {
//                    item { GanttTableHeader(ganttDatesAsDates) }
//                    itemsIndexed(ganttValue) { _, rowData ->
//                        GanttTableRow(rowData, ganttDatesAsDates, hoursGanttData)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun GanttTableHeaderCell(
//    text: String
//) {
//    Box(
//        modifier = Modifier
//            .sizeIn(minHeight = 65.dp)
//            .width(90.dp)
//            .border(BorderStroke(1.dp, Color.Black)), contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = text, Modifier.padding(8.dp), color = Color.Black
//        )
//    }
//}
//
//@Composable
//fun GanttFirstTableDataCell(
//    text: String
//) {
//    Box(
//        modifier = Modifier
//            .width(90.dp)
//            .height(40.dp)
//            .border(BorderStroke(1.dp, Color.Black))
//            .background(Color.LightGray), contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = text, Modifier.padding(8.dp), color = Color.Black
//        )
//    }
//}
//
//@Composable
//fun GanttTableDataCell(backgroundColor: Color) {
//    Box(
//        modifier = Modifier
//            .border(1.dp, Color.Black)
//            .width(90.dp)
//            .height(40.dp)
//            .background(backgroundColor)
//    ) {}
//}
//
//fun String.toGanttDate(): Date? {
//    if (this == null || this == "null") {
//        return null
//    }
//    val format = "yyyy-MM-dd"
//    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
//    try {
//        return sdf.parse(this)
//    } catch (e: ParseException) {
//        // ignore
//    }
//    return null
//}
