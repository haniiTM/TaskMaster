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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.AndroidDownloader
import com.example.taskmaster.android.ui.activity.MainActivityViewModel
import com.example.taskmaster.android.ui.screens.login_screen.LoginViewModel
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import com.example.taskmaster.data.data_sources.AuthRepositoryImpl
import com.example.taskmaster.domain.repositories.AuthRepository
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.get
import java.io.File

@Composable
fun CalculationTimeButton(
    projectId: Int?,
    viewModelURP: UserroleprojectViewModel = getViewModel(),
    manHoursViewModel: ManHoursViewModel = getViewModel(),
    mainActivityViewModel: MainActivityViewModel = getViewModel(),
) {
    // Получение контекста через LocalContext.current
    val context = LocalContext.current


    // Тестрование получение ответа от сервера
    if(projectId != null) {
        LaunchedEffect(key1 = true) {
            viewModelURP.fetchFile(projectId)
        }
    }

    Button(
        onClick = {
            if(viewModelURP.stateFile.value.itemState != null && projectId != null &&
                mainActivityViewModel.accessToken.value?.tokenLong != null) {
                val downloader = AndroidDownloader(context)

                downloader.downloadFile(
                    url = "http://5.35.85.206:8080/user_role_project/excel/$projectId",
                    token = mainActivityViewModel.accessToken.value?.tokenLong!!,
                    nameFile = viewModelURP.stateFile.value.itemState!!
                )
            }
        },
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

