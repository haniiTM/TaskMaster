package com.example.taskmaster.di

import com.example.taskmaster.domain.use_cases.AccessTokenUseCase
import com.example.taskmaster.domain.use_cases.ProjectListUseCase
import com.example.taskmaster.domain.utils.DatabaseDriverFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }

    single { AccessTokenUseCase(get()) }
    single { ProjectListUseCase(get()) }
}

object KoinHelper : KoinComponent {
    fun getAccessTokenDtoUseCase() = get<AccessTokenUseCase>()
    fun getProjectListUseCase() = get<ProjectListUseCase>()
}