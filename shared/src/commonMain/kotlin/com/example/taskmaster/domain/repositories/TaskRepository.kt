package com.example.taskmaster.domain.repositories

import com.example.taskmaster.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun fetchTask(): Flow<Task?>
}