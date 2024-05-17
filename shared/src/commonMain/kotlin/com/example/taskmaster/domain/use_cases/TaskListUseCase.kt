package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.TaskDTO
import org.koin.core.component.KoinComponent

class TaskListUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getUncompletedTaskList(idProj: Number): MutableList<TaskDTO?> {
        return apiService.fetchUnfulfilleddTask(idProj)
    }

    suspend fun getCompletedTaskList(idProj: Number): MutableList<TaskDTO?> {
        return apiService.fetchCompletedTask(idProj)
    }
}