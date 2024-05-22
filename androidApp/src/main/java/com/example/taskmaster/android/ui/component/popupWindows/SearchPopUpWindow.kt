package com.example.taskmaster.android.ui.component.popupWindows

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox

@Composable
fun SearchPopUpWindow(onDismissRequest: () -> Unit){
    var searchQuery by remember {
        mutableStateOf("")
    }
    val linearGradient =
        Brush.verticalGradient(listOf(MaterialTheme.colorScheme.onPrimary, MaterialTheme.colorScheme.onSecondary))

    Box(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(15.dp))
        .border(
            BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
            shape = RoundedCornerShape(15.dp)
        )
        .background(linearGradient)){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.padding(start = 26.dp, end = 26.dp, top = 15.dp )){
                UnifiedTextBox(
                    value = searchQuery,
                    onValueChange = { newValue -> searchQuery = newValue },
                    placeholder = "Что ищем?",
                    roundedTopAngle = 10,
                    roundedDownAngle = 10
                )
            }

            Button(
                onClick = { onDismissRequest() },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 11.dp, bottom = 16.dp)
                    .width(108.dp)
                    .height(30.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.White), contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = "Найти", color = Color.Black, fontWeight = FontWeight.Normal, fontSize = 12.sp)
            }
        }
    }
}


@Composable
fun FilteredList(data: List<String>, query: String) {
    val filteredData = remember(query) {
        data.filter { it.contains(query, ignoreCase = true) }
    }

    LazyColumn {
        items(filteredData) { item ->
            Text(text = item, modifier = Modifier.padding(16.dp))
        }
    }
}