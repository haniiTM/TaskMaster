package com.example.taskmaster.android.di

import com.example.taskmaster.android.ui.activity.MainActivityViewModel
import com.example.taskmaster.android.ui.screens.login_screen.LoginViewModel
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainActivityViewModel(authRepository = get()) }
    viewModel { LoginViewModel(authRepository = get()) }
    viewModel { TaskViewModel(apiService = get()) }
}