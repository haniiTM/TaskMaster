package com.example.taskmaster.android.ui.component.authScreenItems

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmaster.android.R
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AuthBlock(navController: NavController, viewModel: LoginViewModel = getViewModel()) {
    var isValid = false
    var context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    var userLogin by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var isInternetConnected by remember { mutableStateOf(isInternetAvailable(context)) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Check every second
            val newState = isInternetAvailable(context)
            if (newState != isInternetConnected) {
                isInternetConnected = newState
            }
        }
    }

    Box(modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)
        ) {
            BasicTextField(
                value = userLogin,
                onValueChange = { userLogin = it },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp, 5.dp))
                    .background(color = Color.White)
                    .height(40.dp)
                    .width(278.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                decorationBox = @Composable { innerTextField ->
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = userLogin,
                        innerTextField = innerTextField,
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        ),
                        singleLine = true,
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        placeholder = { Text("Логин") })
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            BasicTextField(
                value = userPassword,
                onValueChange = { userPassword = it },
                modifier = Modifier
                    .background(color = Color.White)
                    .height(40.dp)
                    .width(278.dp),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    if (isInternetConnected) {
                        viewModel.dataToken(userLogin, userPassword).observeForever { success ->
                            isValid = success
                            if (success) {
                                navController.navigate("projects")
                            } else {
                                userLogin = ""
                                userPassword = ""
                                Toast.makeText(
                                    context,
                                    "Неверный логин или пароль",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Отсутствует подключение к сети", Toast.LENGTH_LONG)
                            .show()
                    }
                    keyboardController?.hide()
                }),
                decorationBox = @Composable { innerTextField ->
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = userPassword,
                        innerTextField = innerTextField,
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        ),
                        singleLine = true,
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        placeholder = { Text("Пароль") },
                        trailingIcon = {
                            if (passwordVisible) {
                                Icon(
                                    painter = painterResource(id = R.drawable.crossed_out_eye_icon),
                                    contentDescription = "",
                                    tint = Color.Black,
                                    modifier = Modifier.clickable {
                                        passwordVisible = !passwordVisible
                                    }
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.eye_icon),
                                    contentDescription = "",
                                    tint = Color.Black,
                                    modifier = Modifier.clickable {
                                        passwordVisible = !passwordVisible
                                    }
                                )
                            }
                        })
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (isInternetConnected) {
                        viewModel.dataToken(userLogin, userPassword).observeForever { success ->
                            isValid = success
                            if (success) {
                                navController.navigate("projects")
                            } else {
                                userLogin = ""
                                userPassword = ""
                                Toast.makeText(
                                    context,
                                    "Неверный логин или пароль",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Отсутствует подключение к сети", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                modifier = Modifier
                    .width(278.dp)
                    .height(40.dp), colors = ButtonDefaults.buttonColors(
                    Color.White
                ), shape = RoundedCornerShape(bottomEnd = 5.dp, bottomStart = 5.dp)
            ) {
                Text(
                    text = "Войти", fontSize = 12.sp, color = Color.Black
                )
            }
        }
    }
}
