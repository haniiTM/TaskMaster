package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.popupWindows.MaskVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnifiedTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    passwordVisibleValue: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardType: KeyboardType = KeyboardType.Email,
    roundedTopLeftAngle: Int = 0,
    roundedTopRightAngle: Int = 0,
    roundedDownRightAngle: Int = 0,
    roundedDownLeftAngle: Int = 0,
    spacer: Int = 0,
    borderWidth: Int = 0,
    icon: Int = 0,
    changeIcon: Int = 0,
    prefix: @Composable (() -> Unit)? = null,
    timeUnifiedTextFieldKey: Boolean = false,
    passwordTransformationKey: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean = false,
    clearUnit: () -> Unit = {},
    auth: Boolean = false
) {
    val passwordVisible = remember { mutableStateOf(passwordVisibleValue) }
    val timeMask = MaskVisualTransformation("##:##")
    val textFieldModifier = Modifier
        .clip(
            RoundedCornerShape(
                roundedTopLeftAngle.dp,
                roundedTopRightAngle.dp,
                roundedDownRightAngle.dp,
                roundedDownLeftAngle.dp
            )
        )
        .border(
            BorderStroke(borderWidth.dp, MaterialTheme.colorScheme.outline),
            shape = RoundedCornerShape(
                roundedTopLeftAngle.dp,
                roundedTopRightAngle.dp,
                roundedDownRightAngle.dp,
                roundedDownLeftAngle.dp
            )
        )
        .background(color = Color.White)
        .height(TextFieldHeight)
        .fillMaxWidth()

    val visualTransformation = when {
        !passwordVisible.value && passwordTransformationKey -> PasswordVisualTransformation()
        timeUnifiedTextFieldKey -> timeMask
        else -> VisualTransformation.None
    }

    BasicTextField(value = value,
        onValueChange = if (timeUnifiedTextFieldKey) { newValue ->
            onValueChange(newValue.filter { it.isDigit() }.take(4))
        } else onValueChange,
        modifier = textFieldModifier,
        singleLine = true,
        enabled = enabled,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
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
                    if (isError) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.warning),
                                contentDescription = "",
                                tint = Color.Red,
                                modifier = Modifier.size(15.dp)
                            )
                            if (icon != 0) {
                                Icon(painter = painterResource(
                                    id = if (changeIcon != 0) {
                                        if (passwordVisible.value) icon else changeIcon
                                    } else {
                                        icon
                                    }
                                ),
                                    contentDescription = "Toggle Password Visibility",
                                    tint = Color.Black,
                                    modifier = Modifier.clickable {
                                        passwordVisible.value = !passwordVisible.value
                                    })
                            }
                        }
                    } else {
                        if (icon != 0) {
                            Icon(painter = painterResource(
                                id = if (changeIcon != 0) {
                                    if (passwordVisible.value) icon else changeIcon
                                } else {
                                    icon
                                }
                            ),
                                contentDescription = "Toggle Password Visibility",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    if(auth){
                                        passwordVisible.value = !passwordVisible.value
                                    }else{
                                        clearUnit()
                                    }
                                })
                        }
                    }

                },
                prefix = prefix
            )
        })
    Spacer(modifier = Modifier.height(spacer.dp))
}

private val TextFieldHeight = 40.dp