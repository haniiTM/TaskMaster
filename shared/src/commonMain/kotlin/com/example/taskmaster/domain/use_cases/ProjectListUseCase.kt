package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.TaskDTO
import org.koin.core.component.KoinComponent

class ProjectListUseCase(private val apiService: ApiService) : KoinComponent {
    suspend fun getProjectList(): MutableList<TaskDTO?> {
        return apiService.fetchProject()
    }

    suspend fun createProject(projectName: String) {
        return apiService.createProject(projectName)
    }

    suspend fun deleteProject(projectId: Int) {
        return apiService.DeleteTaskOrProject(projectId)
    }
}