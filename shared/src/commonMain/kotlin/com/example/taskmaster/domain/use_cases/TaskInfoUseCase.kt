package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.ActivityDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import com.example.taskmaster.data.network.models.PersonDTO
import com.example.taskmaster.data.network.models.StatusDTO
import com.example.taskmaster.data.network.models.TaskByID
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
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

    suspend fun getTaskUserList(taskId: Int): MutableList<PersonDTO?> {
        return apiService.fetchPersonInTask(taskId)
    }

    suspend fun getFreeFromTaskUserList(taskId: Int): MutableList<PersonDTO?> {
        return apiService.fetchPersonFreeForTask(taskId)
    }

    suspend fun deleteUserFromTask(userId: Int, taskId: Int): Boolean {
        return apiService.deletePersonFromTask(taskId, userId)
    }

    suspend fun addUserToTask(urp: UserRoleProjectDTO): Boolean {
        return apiService.linkUserTaskOrProject(urp)
    }

    suspend fun updateHoursSpent(urp: UserRoleProjectDTO, taskId: Int): Boolean {
        return apiService.updateHoursSpent(urp, taskId)
    }

    suspend fun updateEstimatedTime(taskId: Int, taskDTO: TaskDTO): Boolean {
        return apiService.updateTimeEstimation(taskDTO, taskId)
    }

    suspend fun getTaskListForDependence(projId: Int, taskId: Int): MutableList<TaskDTO?> {
        return apiService.fetchTaskForDependence(projId, taskId)
    }

    suspend fun addDependency(taskDependent: Int, taskDependsOn: Int): Boolean {
        return apiService.addDependenceForTask(taskDependent, taskDependsOn)
    }

    suspend fun deleteDependency(dependenceOn: Int): Boolean {
        return apiService.deleteDependence(dependenceOn)
    }

    suspend fun getTypeOfActivityList(): MutableList<TypeOfActivityDTO?> {
        return apiService.fetchTypeOfActivity()
    }

    suspend fun updateTask(taskId: Int, taskDTO: TaskDTO): Boolean {
        return apiService.updateTask(taskDTO, taskId)
    }

    suspend fun getStatusList(): MutableList<StatusDTO?> {
        return apiService.fetchStatus()
    }
}