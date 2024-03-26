package com.example.taskmaster.data.data_sources

import com.example.taskmaster.data.mappers.toDomain
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.domain.models.Task
import com.example.taskmaster.domain.repositories.AuthRepository
import com.example.taskmaster.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

//class TaskRepositoryImpl constructor(
//    private val apiService: ApiService,
//) : AuthRepository {
//    override suspend fun fetchTask(): Flow<Task?> {
//        val response = apiService.fetchTaskDto()
//        return flowOf(response?.toDomain())
//    }
//}