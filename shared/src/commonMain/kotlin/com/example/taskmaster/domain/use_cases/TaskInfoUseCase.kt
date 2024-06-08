package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.ActivityDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import com.example.taskmaster.data.network.models.TaskByID
import org.koin.core.component.KoinComponent

class TaskInfoUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getTaskInfo(taskId: Number): TaskByID? {
        return apiService.fetchTaskById(taskId)
    }

    suspend fun createLaborCost(manHour: ManHoursDTO, taskId: Int): Boolean {
        return apiService.createManHours(manHour, taskId)
    }

    suspend fun getActivityList(): MutableList<ActivityDTO?> {
        return apiService.fetchActivity()
    }
}