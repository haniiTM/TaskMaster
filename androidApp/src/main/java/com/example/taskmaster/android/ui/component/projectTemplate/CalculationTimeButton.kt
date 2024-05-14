package com.example.taskmaster.android.ui.component.projectTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CalculationTimeButton(
    projectId: Int?,
    viewModelURP: UserroleprojectViewModel = getViewModel(),
    manHoursViewModel: ManHoursViewModel = getViewModel()
) {
    // Тестрование получение ответа от сервера
    if(projectId != null) {
        LaunchedEffect(key1 = true) {
            //viewModelURP.getCalendarPlan(projectId)
            manHoursViewModel.getReportManHours(projectId)
        }
    }

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, bottom = 14.dp)
                ,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ), border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Text(
            text = "Расчет времени по трудозатратам", fontSize = 12.sp
        )
    }
}
