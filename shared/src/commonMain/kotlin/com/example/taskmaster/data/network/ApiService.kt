package com.example.taskmaster.data.network

import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.ActivityDTO
import com.example.taskmaster.data.network.models.DescriptionDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import com.example.taskmaster.data.network.models.RegisterReceiveRemote
import com.example.taskmaster.data.network.models.StatusDTO
import com.example.taskmaster.data.network.models.TaskByID
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO

interface ApiService {
    suspend fun fetchUserToken(login: String, password: String): AccessTokenDto?
    suspend fun fetchProject(): MutableList<TaskDTO?>
    suspend fun fetchTask(idProj: Number): MutableList<TaskDTO?>
    suspend fun fetchTaskById(taskId: Number): TaskByID?
    suspend fun createProject(nameProject: String)
    suspend fun createTask(task: TaskDTO, parentId: Int)
    suspend fun fetchTypeOfActivity(): MutableList<TypeOfActivityDTO?>
    suspend fun fetchStatus(): MutableList<StatusDTO?>
    suspend fun fetchCompletedTask(idProj: Number): MutableList<TaskDTO?>
    suspend fun fetchUnfulfilleddTask(idProj: Number): MutableList<TaskDTO?>
    suspend fun updateStatusTask(taskId: Int, statusId: Int, nameTask: String)
    suspend fun DeleteTaskOrProject(taskId: Int)
    suspend fun fetchDescription(descrId: Int): MutableList<DescriptionDTO?>
    suspend fun registerUser(user: RegisterReceiveRemote)
    suspend fun createManHours(manHour: ManHoursDTO, taskId: Int)
    suspend fun fetchManHours(taskId: Int): MutableList<ManHoursDTO?>
    suspend fun fetchActivity(): MutableList<ActivityDTO?>
}

