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
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.navigation.NavigationItem

@Composable
fun CalculationTimeButton(
    navController: NavController,
    projectId: Int?,
    title: String?
) {
    Button(
        onClick = {
            navController.navigate(
                NavigationItem.CalculationOfLaborCosts.passIdAndTitle(
                    projectId!!.toInt(),
                    title = title!!
                )
            )
        },
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, bottom = 14.dp),
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
