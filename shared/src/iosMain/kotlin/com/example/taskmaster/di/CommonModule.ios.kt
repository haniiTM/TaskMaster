package com.example.taskmaster.di

import com.example.taskmaster.domain.use_cases.GetAccessTokenDtoUseCase
import com.example.taskmaster.domain.utils.DatabaseDriverFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }
    single { GetAccessTokenDtoUseCase(repository = get()) }
}

object KoinHelper : KoinComponent {
    fun getAccessTokenDtoUseCase() = get<GetAccessTokenDtoUseCase>()
}