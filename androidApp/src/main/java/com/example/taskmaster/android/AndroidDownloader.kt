package com.example.taskmaster.android

import android.Manifest
import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.taskmaster.android.ui.screens.login_screen.LoginViewModel
import org.koin.androidx.compose.getViewModel
import java.io.File
import java.io.FileOutputStream

class AndroidDownloader(
    private val context: Context,
): Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(
        url: String,
        token: String,
        nameFile: String
    ): Long {
        val request = DownloadManager.Request(url.toUri())
        request.setTitle(nameFile);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.addRequestHeader("Authorization", "Bearer ${token}")
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nameFile);
        return downloadManager.enqueue(request)
    }
}

