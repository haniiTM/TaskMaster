package com.example.taskmaster.android.ui.component.taskInfoItems

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.DropdownMenuArea
import com.example.taskmaster.android.ui.component.popupWindows.LaborCostInfo
import com.example.taskmaster.data.network.models.ManHoursDTO

@Composable
fun ListItem(name: String, item : ManHoursDTO, attachmentsListFlag: Boolean){
    var showLaborCostInfo by remember {
        mutableStateOf(false)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clickable { showLaborCostInfo = !showLaborCostInfo }, contentAlignment = Alignment.CenterStart
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(text = name, modifier = Modifier.padding(horizontal = 12.dp), color = Color.Black)
            if (attachmentsListFlag) {
                DropdownMenuArea(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Log.d("expanded", expanded.toString())
                        Icon(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = "menu_icon",
                            tint = Color.Black
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Скачать", color = Color.Black) },
                                onClick = {
                                    expanded = !expanded
                                },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.download_icon),
                                        contentDescription = "download_icon",
                                        tint = Color.Black
                                    )
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Удалить", color = Color.Red) },
                                onClick = {
                                    expanded = !expanded
                                },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.delete_icon),
                                        contentDescription = "delete_icon",
                                        tint = Color.Red
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    Divider (
        color = MaterialTheme.colorScheme.outline,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
    if(showLaborCostInfo) {
        Dialog(onDismissRequest = { showLaborCostInfo = !showLaborCostInfo }) {
            LaborCostInfo(name, item)
        }
    }
}
