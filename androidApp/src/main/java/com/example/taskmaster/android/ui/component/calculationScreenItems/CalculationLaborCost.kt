package com.example.taskmaster.android.ui.component.calculationScreenItems

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import org.koin.androidx.compose.getViewModel
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
    data: Pair<Int?, String?>,
    dates: List<Date?>,
    hoursData: List<Triple<Date?, String?, Int?>>
) {
    val uniqueDates = dates.distinct().filterNotNull().sorted()

    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    ) {
        FirstTableDataCell(text = data.first.toString())

        uniqueDates.forEach { date ->
            val matchingEntry = hoursData.find { it.first == date && it.third == data.first }
            val matchingHour = matchingEntry?.second ?: "-"
            TableDataCell(text = matchingHour)
        }
    }
}

@Composable
fun CalculationOfLaborCosts(
    laborCostViewModel: ManHoursViewModel = getViewModel(),
    testViewModel: UserroleprojectViewModel = getViewModel(),
    id: Int?
) {
    Log.d("project id", id.toString())
    LaunchedEffect(key1 = id) {
        laborCostViewModel.getReportManHours(id!!)
        testViewModel.getCalendarPlan(id!!)
    }
    val laborCosts = laborCostViewModel.stateManHoursReport.value.itemState
    val dates = laborCosts.map { it?.createdAt?.toDate() }
    Log.d("dates", dates.toString())
    val labors = laborCosts.map {
        Pair(it?.taskId ?: -1, it?.hoursSpent ?: "-")
    }.sortedBy { it.first }.distinct()
    Log.d("labors", labors.toString())
    val uniqueDates = dates.distinct().filterNotNull().sorted()
    val hoursData = laborCosts.map {
        Triple(it?.createdAt?.toDate(), it?.hoursSpent ?: "-", it?.taskId)
    }
    Box(
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, top = 26.dp, bottom = 13.dp)
            .height(200.dp)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(15.dp))
    ) {
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
                        TableRow(rowData, dates, hoursData)
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
    text: String
) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(40.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color.LightGray), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, Modifier.padding(8.dp), color = Color.Black
        )
    }
}

@Composable
fun TableDataCell(
    text: String
) {
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

fun String.toDate(): Date? {
    if (this == null || this == "null") {
        return null
    }
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    return sdf.parse(this)
}

fun formatDate(date: Date): String {
    val sdf = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
    return sdf.format(date)
}