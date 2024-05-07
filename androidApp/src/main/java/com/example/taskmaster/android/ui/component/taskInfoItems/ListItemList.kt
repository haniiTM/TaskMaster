package com.example.taskmaster.android.ui.component.taskInfoItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ListItemList(
    viewModel: ManHoursViewModel = getViewModel(),
    taskId: Int
) {
    LaunchedEffect(key1 = true) {
        viewModel.getManHours(taskId)
    }

    var laborCosts = listOf("1", "2", "3", "4", "5", "6", "7")
    Box(
        modifier = Modifier
            .padding(start = 14.dp, top = 26.dp, end = 14.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color.White)
    ) {
        LazyColumn {
            itemsIndexed(viewModel.state.value.itemState) { _, item ->
                if (item != null) {
                    ListItem(name = item.comment ?: "")
                }
            }
        }
    }
}