package com.example.taskmaster.android

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

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