package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.PersonDTO
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
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

    suspend fun updateStatus(
        taskId: Int,
        statusId: Int,
        nameTask: String
    ) {
        return apiService.updateStatusTask(taskId, statusId, nameTask)
    }

    suspend fun getProjectUserList(projectId: Int): MutableList<PersonDTO?> {
        return apiService.fetchPersonInProject(projectId)
    }

    suspend fun deleteProjectUser(projectId: Int, userId: Int) {
        return apiService.deletePersonFromProject(projectId, userId)
    }

    suspend fun getFreeFromProjectUserList(projectId: Int): MutableList<PersonDTO?> {
        return apiService.fetchPersonFreeFromProject(projectId)
    }

    suspend fun linkUserToProject(urp: UserRoleProjectDTO): Boolean {
        return apiService.linkUserTaskOrProject(urp)
    }

    suspend fun updateTaskDesc(id: Int, desc: String) {
        return apiService.updateDescriptionTask(id, desc)
    }
}