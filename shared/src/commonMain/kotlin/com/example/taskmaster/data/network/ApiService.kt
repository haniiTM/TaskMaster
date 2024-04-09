package com.example.taskmaster.data.network

import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO

interface ApiService {
    suspend fun fetchUserToken(login: String, password: String): AccessTokenDto?
    suspend fun fetchProject(): MutableList<TaskDTO?>
    suspend fun fetchTask(idProj: Number): MutableList<TaskDTO?>

    suspend fun createProject(nameProject: String)
    suspend fun fetchTypeOfActivity(): MutableList<TypeOfActivityDTO?>
}

