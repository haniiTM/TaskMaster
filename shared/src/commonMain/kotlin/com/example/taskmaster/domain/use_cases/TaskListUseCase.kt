package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import org.koin.core.component.KoinComponent

class TaskListUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getUncompletedTaskList(idProj: Number): MutableList<TaskDTO?> {
        return apiService.fetchUnfulfilleddTask(idProj)
    }

    suspend fun getCompletedTaskList(idProj: Number): MutableList<TaskDTO?> {
        return apiService.fetchCompletedTask(idProj)
    }

    suspend fun getTaskCategoryList(): MutableList<TypeOfActivityDTO?> {
        return apiService.fetchTypeOfActivity()
    }

    suspend fun createTask(task: TaskDTO, parentId: Int) {
        return apiService.createTask(task, parentId)
    }

    suspend fun deleteTask(id: Int) {
        return apiService.DeleteTaskOrProject(id)
    }
}