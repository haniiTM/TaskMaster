package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.TaskByID
import org.koin.core.component.KoinComponent

class TaskInfoUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getTaskInfo(taskId: Number): TaskByID? {
        return apiService.fetchTaskById(taskId)
    }
}