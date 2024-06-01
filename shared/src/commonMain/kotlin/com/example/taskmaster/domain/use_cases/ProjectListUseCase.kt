package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.PersonDTO
import com.example.taskmaster.data.network.models.RegisterReceiveRemote
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
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

    suspend fun getUserRoleList(): MutableList<TypeOfActivityDTO?> {
        return apiService.fetchTypeOfActivity()
    }

    suspend fun createUser(user: RegisterReceiveRemote) {
        return apiService.registerUser(user)
    }

    suspend fun getUserList(): MutableList<PersonDTO?> {
        return apiService.fetchAllPerson()
    }

    suspend fun deleteUser(id: MutableList<Int>) {
        return apiService.deletePersonFromSystem(id)
    }
}