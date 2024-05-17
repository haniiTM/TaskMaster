package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmaster.android.AndroidDownloader
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.activity.MainActivityViewModel
import com.example.taskmaster.android.ui.component.calculationScreenItems.CalculationOfLaborCosts
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CalculationOfLaborCostsScreen(
    navController: NavController,
    result: Int?,
    title: String?,
    viewModelURP: UserroleprojectViewModel = getViewModel(),
    mainActivityViewModel: MainActivityViewModel = getViewModel(),
    manHoursViewModel: ManHoursViewModel = getViewModel(),
) {
    val context = LocalContext.current

    if(result != null) {
        LaunchedEffect(key1 = true) {
            //viewModelURP.fetchFile(result)
            manHoursViewModel.fetchFileForManHours(result)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(
            text = title ?: "Заголовок отсутствует",
            iconItem = R.drawable.more,
            actionIcons = listOf(
                R.drawable.search1_icon, R.drawable.users_icon
            ),
            navController = navController,
            actionTitle = listOf("Поиск", "Пользователи"),
            projectScreenKey = false,
            projectId = result
        )
        Text(text = "Трудозатраты", color = MaterialTheme.colorScheme.onTertiary, fontSize = 20.sp)
        CalculationOfLaborCosts(id = result)
        Button(
            onClick = {
                if(manHoursViewModel.stateFileManHours.value.itemState != null && result != null &&
                    mainActivityViewModel.accessToken.value?.tokenLong != null) {
                    val downloader = AndroidDownloader(context)

                    downloader.downloadFile(
                        url = "http://5.35.85.206:8080/manhours/excelreport/$result",
                        token = mainActivityViewModel.accessToken.value?.tokenLong!!,
                        nameFile = manHoursViewModel.stateFileManHours.value.itemState!!
                    )
                }
            },
            modifier = Modifier.width(100.dp),
            colors = ButtonDefaults.buttonColors(
                Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(12.dp, 0.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Text(text = "Скачать", color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.download_icon),
                contentDescription = "download_icon",
                tint = Color.Black
            )
        }
    }
}