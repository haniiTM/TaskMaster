package com.example.taskmaster.di

import com.russhwolf.settings.Settings
import com.example.taskmaster.data.cache.sqldelight.AccessTokenDao
import com.example.taskmaster.data.data_sources.AuthRepositoryImpl
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.ApiServiceImpl
import com.example.taskmaster.data.network.utils.TokenInterceptor
import com.example.taskmaster.domain.repositories.AuthRepository
import com.example.taskmaster.domain.repositories.TaskRepository
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {

    single { Settings() }

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient {
            defaultRequest {
                header("Authorization", TokenInterceptor().invoke())
            }

            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.e(tag = "Http Client", message = message)
                    }
                }
            }

            install(ContentNegotiation) {
                json()
            }
        }
    }
    single<ApiService> { ApiServiceImpl(httpClient = get()) }

    single { AccessTokenDao(databaseDriverFactory = get()) }

    single<AuthRepository> { AuthRepositoryImpl(apiService = get(), accessTokenDao = get()) }

}

expect fun platformModule(): Module