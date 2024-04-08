package com.example.taskmaster.android.ui.component.popupWindows

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPopUpWindow(onDismissRequest: () -> Unit,){
    var searchQuery by remember {
        mutableStateOf("")
    }
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth().background(Color.Red)){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BasicTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.padding(start = 26.dp, end = 26.dp, top = 19.dp, bottom = 15.dp)
                    .background(color = Color.White)
                    .height(35.dp)
                    .fillMaxWidth(),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                decorationBox = @Composable { innerTextField ->
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = searchQuery,
                        innerTextField = innerTextField,
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        ),
                        singleLine = true,
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        placeholder = { Text("Что ищем?") })
                }
            )
            Button(
                onClick = { onDismissRequest() },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.defaultMinSize(minWidth = 72.dp).padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.White)
            ) {
                Text(text = "Найти", color = Color.Black, fontWeight = FontWeight.Normal)
            }
        }
    }
}

@Preview
@Composable
fun pre(){
    SearchPopUpWindow(onDismissRequest = {})
}
