package com.example.taskmaster.domain.repositories

import com.example.taskmaster.core.database.AccessTokenEntity
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.domain.models.AccessToken
import com.example.taskmaster.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun fetchUserToken(login: String, password: String)
    suspend fun getUserToken(): Flow<AccessToken?>
    suspend fun saveUserToken(accessToken: AccessTokenEntity)
    suspend fun deleteUserToken()
    suspend fun fetchTask(): Flow<TaskDTO?>

}
