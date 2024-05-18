package com.example.taskmaster.di

import com.example.taskmaster.domain.use_cases.AccessTokenUseCase
import com.example.taskmaster.domain.use_cases.LaborCostListUseCase
import com.example.taskmaster.domain.use_cases.ProjectListUseCase
import com.example.taskmaster.domain.use_cases.TaskInfoUseCase
import com.example.taskmaster.domain.use_cases.TaskListUseCase
import com.example.taskmaster.domain.utils.DatabaseDriverFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }

    single { AccessTokenUseCase(get()) }
    single { ProjectListUseCase(get()) }
    single { TaskListUseCase(get()) }
    single { TaskInfoUseCase(get()) }
    single { LaborCostListUseCase(get()) }
}

object KoinHelper : KoinComponent {
    fun getAccessTokenDtoUseCase() = get<AccessTokenUseCase>()
    fun getProjectListUseCase() = get<ProjectListUseCase>()
    fun getTaskListUseCase() = get<TaskListUseCase>()
    fun getTaskInfoUseCase() = get<TaskInfoUseCase>()
    fun getLaborCostListUseCase() = get<LaborCostListUseCase>()
}