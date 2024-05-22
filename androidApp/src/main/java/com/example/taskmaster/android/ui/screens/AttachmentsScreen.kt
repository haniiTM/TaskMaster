package com.example.taskmaster.android.ui.screens

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.projectTemplate.BoxButton
import com.example.taskmaster.android.ui.component.taskInfoItems.ListItemList
import com.example.taskmaster.android.ui.screens.description_screen.DescriptionViewModel
import org.koin.androidx.compose.getViewModel
import java.io.ByteArrayOutputStream
import java.io.InputStream

@Composable
fun AttachmentsListScreen(
    navController: NavController,
    id: Int?,
    title: String?,
    descriptionViewModel: DescriptionViewModel = getViewModel()
){
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var selectedFileName by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
        uri?.let {
            val byteArray = context.contentResolver.openInputStream(it)?.use { inputStream ->
                getByteArrayFromInputStream(inputStream)
            }

            selectedFileName = uri?.let { getFileName(context, it) }

            descriptionViewModel.uploadFile(
                taskId = id!!,
                data = byteArray!!,
                fileName = selectedFileName!!
            )
            byteArray?.let { bytes ->
                Log.d("ByteArray", bytes.contentToString())
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(
            text = title ?: "Заголовок отсутствует",
            actionIcons = listOf(
                R.drawable.search1_icon, R.drawable.users_icon
            ),
            navController = navController,
            actionTitle = listOf("Поиск", "Пользователи")
        )
        ListItemList(taskId = id ?: 0, attachmentsListFlag = true)
        BoxButton(text = "Добавить вложение", cardContainerFlag = false) {
            filePickerLauncher.launch("application/*")
        }
        selectedFileUri?.let { uri ->
            val fileName = getFileName(context, uri)
            Log.d("FileName", fileName.toString())
        }
    }
}

fun getByteArrayFromInputStream(inputStream: InputStream): ByteArray {
    val buffer = ByteArrayOutputStream()
    val data = ByteArray(25024)
    var nRead: Int
    while (inputStream.read(data, 0, data.size).also { nRead = it } != -1) {
        buffer.write(data, 0, nRead)
    }
    buffer.flush()
    return buffer.toByteArray()
}

fun getFileName(context: Context, uri: Uri): String? {
    var name: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex != -1) {
                name = it.getString(nameIndex)
            }
        }
    }
    return name
}