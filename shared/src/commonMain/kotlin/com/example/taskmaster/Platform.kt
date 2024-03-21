package com.example.taskmaster

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform