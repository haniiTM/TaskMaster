package com.example.taskmaster.utils

import com.example.taskmaster.di.commonModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(commonModule)
    }.koin
}