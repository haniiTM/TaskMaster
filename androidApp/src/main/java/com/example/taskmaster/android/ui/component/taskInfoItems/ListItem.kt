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
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.android.AndroidDownloader
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.activity.MainActivityViewModel
import com.example.taskmaster.android.ui.component.commonTemplate.DropdownMenuArea
import com.example.taskmaster.android.ui.component.popupWindows.LaborCostInfo
import com.example.taskmaster.android.ui.screens.description_screen.DescriptionViewModel
import com.example.taskmaster.data.network.models.FileDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import org.koin.androidx.compose.getViewModel

@Composable
fun ListItem(
    taskId: Int?,
    name: String? = null,
    itemManHours: ManHoursDTO? = null,
    itemFile: FileDTO? = null,
    descriptionId: Int? = null,
    fileId: Int? = null,
    mainActivityViewModel: MainActivityViewModel = getViewModel(),
    descriptionViewModel: DescriptionViewModel = getViewModel(),
    attachmentsListFlag: Boolean
) {
    var showLaborCostInfo by remember {
        mutableStateOf(false)
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val maxWidth = (screenWidth * 0.6f)
    val parts = name?.split(".")
    var fileName = ""
    var extension = ""
    if (parts?.size == 2) {
        fileName = parts[0]
        extension = "." + parts[1]
    }
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .then(if (!attachmentsListFlag) Modifier.clickable {
                showLaborCostInfo = !showLaborCostInfo
            } else Modifier),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if(attachmentsListFlag){
                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth(.75f)) {
                    Text(text = fileName, modifier = Modifier
                        .padding(start = 12.dp)
                        .sizeIn(maxWidth = maxWidth), color = Color.Black, overflow = TextOverflow.Ellipsis)
                    Text(text = extension, color = Color.Black)
                }
                DropdownMenuArea(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }) {
                    IconButton(onClick = { expanded = !expanded }) {
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
                                    val downloader = AndroidDownloader(context)

                                    downloader.downloadFile(
                                        url = "http://5.35.85.206:8080/description/download/$taskId/${itemFile?.id}",
                                        token = mainActivityViewModel.accessToken.value?.tokenLong!!,
                                        nameFile = name!!
                                    )
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
                                    if(taskId != null && descriptionId != null && fileId != null) {
                                        descriptionViewModel.removeFile(
                                            taskId = taskId,
                                            descriptionId = descriptionId,
                                            fileId = fileId
                                        )
                                    }
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
            }else{
                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth(.75f)) {
                    Text(text = itemManHours?.comment.toString(), modifier = Modifier
                        .padding(start = 12.dp)
                        .sizeIn(maxWidth = maxWidth), color = Color.Black, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
    Divider(
        color = MaterialTheme.colorScheme.outline,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
    if (showLaborCostInfo) {
        Dialog(onDismissRequest = { showLaborCostInfo = !showLaborCostInfo }) {
            LaborCostInfo(name ?: "", itemManHours!!)
        }
    }
}
