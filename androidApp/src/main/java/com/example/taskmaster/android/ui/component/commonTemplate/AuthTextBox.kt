package com.example.taskmaster.android.ui.component.commonTemplate

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
import com.example.taskmaster.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    passwordVisibleValue: Boolean,
    interactionSource: MutableInteractionSource,
    keyboardType: KeyboardType,
    iconVisible: Boolean,
    roundedAngle : Int
) {
    var passwordVisible by remember { mutableStateOf(passwordVisibleValue) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(roundedAngle.dp, roundedAngle.dp))
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), shape = RoundedCornerShape(roundedAngle.dp, roundedAngle.dp))
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
                    if (iconVisible){
                        if (passwordVisible) {
                            Icon(
                                painter = painterResource(id = R.drawable.crossed_out_eye_icon),
                                contentDescription = "Toggle Password Visibility",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                }
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.eye_icon),
                                contentDescription = "Toggle Password Visibility",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                }
                            )
                        }
                    }
                }
            )
        }
    )
    Spacer(modifier = Modifier.height(20.dp))
}