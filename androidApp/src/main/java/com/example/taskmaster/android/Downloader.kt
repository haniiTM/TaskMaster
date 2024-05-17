package com.example.taskmaster.android

interface Downloader {
    fun downloadFile(url: String, token: String, nameFile: String): Long
}