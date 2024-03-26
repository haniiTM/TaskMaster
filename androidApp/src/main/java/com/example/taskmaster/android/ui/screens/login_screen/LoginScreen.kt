package com.example.taskmaster.android.ui.screens.login_screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = getViewModel()) {

    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

    Napier.e("Is loading: ${isLoading.value}")

    viewModel.fetchUserToken("admin", "admin123")
    viewModel.getTask()
}

//fun onResume(context: Context, viewModel: LoginViewModel) {
//    val uri = context.findActivity()?.intent?.data
//
//    if (uri != null && uri.toString().contains(Constants.REDIRECT_URL)) {
//        val code = uri.getQueryParameter("code")
//
//        if (code != null) {
//            Napier.e("Code: $code")
//            viewModel.fetchUserToken(code = code)
//        } else uri.getQueryParameter("error")?.let {
//            Napier.e("Error: $it")
//        }
//    } else {
//        Napier.e("Nothing was returned")
//    }
//}
