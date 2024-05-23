package com.example.taskmaster.android.ui.component.authScreenItems

import AppSettings
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.StateObject.RoleObject
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.navigation.NavigationItem
import com.example.taskmaster.android.ui.screens.login_screen.LoginViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

private fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
        NetworkCapabilities.TRANSPORT_CELLULAR
    ))
}

@Composable
fun AuthBlock(navController: NavController, viewModel: LoginViewModel = getViewModel()) {
    var isValid by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var userLogin by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var isInternetConnected by remember { mutableStateOf(isInternetAvailable(context)) }
    val passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Check every second
            val newState = isInternetAvailable(context)
            if (newState != isInternetConnected) {
                isInternetConnected = newState
            }
        }
    }

    LaunchedEffect(isValid) {
        if (isValid) {
            // Обновить состояние RoleObject.PMOrAdmin после успешного входа
            RoleObject.PMOrAdmin = AppSettings.getUserRole(context)
        }
    }
    Box {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)
                .sizeIn(maxWidth = 278.dp)
        ) {
            UnifiedTextBox(
                value = userLogin,
                onValueChange = { newValue -> userLogin = newValue },
                placeholder = " Логин",
                roundedTopRightAngle = 5,
                roundedTopLeftAngle = 5,
                spacer = 20,
                borderWidth = 1
            )
            UnifiedTextBox(
                value = userPassword,
                onValueChange = { newValue -> userPassword = newValue },
                placeholder = "Пароль",
                passwordVisibleValue = passwordVisible,
                keyboardType = KeyboardType.Password,
                spacer = 20,
                borderWidth = 1,
                icon = R.drawable.crossed_out_eye_icon,
                changeIcon = R.drawable.eye_icon,
                passwordTransformationKey = true
            )
            Button(
                onClick = {
                    if (isInternetConnected) {
                        // success - класс в который хранит токени и флаг, указывающий является ли
                        // пользователь адмнином или прект-менеджером
                        viewModel.dataToken(userLogin, userPassword).observeForever { success ->
                            println(success)
                            AppSettings.setUserRole(context, success.adminOrProjectManager!!)
                            Log.d("AppSettings.setUserRole", success.adminOrProjectManager!!.toString())
                            success?.let {
                                isValid = it.tokenLong!!.isNotEmpty()
                                val result = it.adminOrProjectManager
                                if (isValid) {
                                    AppSettings.setLoginValid(context, isValid)
                                    navController.navigate(
                                        NavigationItem.Projects.passIdAndTitle(success = result!!)
                                    ) {
                                        popUpTo(NavigationItem.Auth.route) { inclusive = true }
                                    }
                                } else {
                                    userPassword = ""
                                    showErrorMessage("Неверный логин или пароль", context = context)
                                }
                            } ?: run {
                                userPassword = ""
                                showErrorMessage("Неверный логин или пароль", context = context)
                            }
                        }
                    } else {
                        showErrorMessage("Отсутствует подключение к сети", context = context)
                    }
                },
                modifier = Modifier
                    .width(278.dp)
                    .height(40.dp)
                    .padding(bottom = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.White
                ),
                shape = RoundedCornerShape(bottomEnd = 5.dp, bottomStart = 5.dp)
            ) {
                Text(
                    text = "Войти",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
    }
}

private fun showErrorMessage(message: String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}