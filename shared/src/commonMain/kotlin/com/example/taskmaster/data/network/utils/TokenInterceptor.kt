package com.example.taskmaster.data.network.utils

import com.example.taskmaster.data.cache.sqldelight.AccessTokenDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TokenInterceptor : KoinComponent {

    private val accessTokenDao: AccessTokenDao by inject()

    operator fun invoke() = runBlocking {
        val str = "Bearer ${accessTokenDao.getToken.first()?.tokenLong ?: ""}"
        println(str)
        "Bearer ${accessTokenDao.getToken.first()?.tokenLong ?: ""}"
    }
}
