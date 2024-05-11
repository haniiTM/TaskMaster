package com.example.taskmaster.android.di

import com.example.taskmaster.android.ui.activity.MainActivityViewModel
import com.example.taskmaster.android.ui.screens.activity_screen.ActivityViewModel
import com.example.taskmaster.android.ui.screens.description_screen.DescriptionViewModel
import com.example.taskmaster.android.ui.screens.login_screen.LoginViewModel
import com.example.taskmaster.android.ui.screens.manHours_screen.ManHoursViewModel
import com.example.taskmaster.android.ui.screens.newUser_screen.NewUserViewModel
import com.example.taskmaster.android.ui.screens.status_screen.StatusViewModel
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.android.ui.screens.type_of_activity.TypeOfActivityViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainActivityViewModel(authRepository = get()) }
    viewModel { LoginViewModel(authRepository = get()) }
    viewModel { TaskViewModel(apiService = get()) }
    viewModel { TypeOfActivityViewModel(apiService = get()) }
    viewModel { StatusViewModel(apiService = get()) }
    viewModel { DescriptionViewModel(apiService = get()) }
    viewModel { NewUserViewModel(apiService = get()) }
    viewModel { ManHoursViewModel(apiService = get()) }
    viewModel { ActivityViewModel(apiService = get()) }
    viewModel { UserroleprojectViewModel(apiService = get()) }
}