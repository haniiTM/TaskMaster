package com.example.taskmaster.android.ui.component.authScreenItems

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.screens.login_screen.LoginViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthBlock(navController: NavController, viewModel: LoginViewModel = getViewModel()) {
    var isValid = false

    val interactionSource = remember { MutableInteractionSource() }
    var userLogin by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
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
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                        placeholder = { Text("Пароль") })
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.dataToken(userLogin, userPassword).observeForever { success ->
                        isValid = success
                        if (success) {
                            navController.navigate("projects")
                        }
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
