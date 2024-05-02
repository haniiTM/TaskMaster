package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun UnifiedTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    passwordVisibleValue: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardType: KeyboardType = KeyboardType.Email,
    roundedTopAngle: Int = 0,
    roundedDownAngle: Int = 0,
    spacer: Int = 0,
    borderWidth: Int = 0,
    icon: Int = 0,
    iconAction: (() -> Unit)? = null,
    changeIcon: Int = 0,
    prefix: @Composable (() -> Unit)? = null
) {
    var passwordVisible by remember { mutableStateOf(passwordVisibleValue) }
    val navController = rememberAnimatedNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    roundedTopAngle.dp,
                    roundedTopAngle.dp,
                    roundedDownAngle.dp,
                    roundedDownAngle.dp
                )
            )
            .border(
                BorderStroke(borderWidth.dp, MaterialTheme.colorScheme.outline),
                shape = RoundedCornerShape(roundedTopAngle.dp, roundedTopAngle.dp)
            )
            .background(color = Color.White)
            .height(40.dp)
            .width(278.dp),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = { Text(placeholder) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                ),
                contentPadding = PaddingValues(horizontal = 10.dp),
                trailingIcon = {
                    if (icon != 0 && currentRoute == "auth") {
                        if (passwordVisible) {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "Toggle Password Visibility",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                }
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = changeIcon),
                                contentDescription = "Toggle Password Visibility",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                }
                            )
                        }
                    } else if (icon != 0 && currentRoute != "auth") {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    } else if (currentRoute == "taskInfo"){
                        IconButton(onClick = { iconAction }) {
                            Icon(painter = painterResource(id = icon), contentDescription = "")
                        }
                    }
                },
                prefix = prefix
            )
        }
    )
    Spacer(modifier = Modifier.height(spacer.dp))
}