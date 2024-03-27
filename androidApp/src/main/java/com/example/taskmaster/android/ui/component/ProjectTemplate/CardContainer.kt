package com.example.taskmaster.android.ui.component.ProjectTemplate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.taskmaster.state.ItemProjectState

@Composable
fun CardContainer(title: String, buttonTitle: String, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 14.dp, end = 14.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .fillMaxWidth()
            .background(com.taskmaster.ui.theme.Gray1),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                modifier = Modifier.padding(top = 14.dp, bottom = 25.dp),
                fontSize = 18.sp,
                color = Color.Black
            )
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .clip(shape = RoundedCornerShape(25.dp))
                    .heightIn(min = 232.dp, max = 345.dp)
            ) {
                itemsIndexed(
                    listOf(
                        ItemProjectState("Сайт Nissan", 72, 4),
                        ItemProjectState("Мобильное приложение Alabuga Tech", 72, 4),
                        ItemProjectState("Мобильное приложение Chudnoi perets", 72, 4),
                        ItemProjectState("Сайт Nissan", 72, 4),
                        ItemProjectState("Мобильное приложение Alabuga Tech", 72, 4),
                        ItemProjectState("Мобильное приложение Chudnoi perets", 72, 4),
                    )
                ) { _, item ->
                    ItemProject(item = item, context = LocalContext.current, navController = navController)
                }
            }
            BoxButton(buttonTitle)
        }

    }
}