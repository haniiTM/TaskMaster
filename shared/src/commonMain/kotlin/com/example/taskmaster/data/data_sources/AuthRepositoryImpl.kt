package com.example.taskmaster.data.data_sources

import com.example.taskmaster.core.database.AccessTokenEntity
import com.example.taskmaster.data.cache.sqldelight.AccessTokenDao
import com.example.taskmaster.data.mappers.toDomain
import com.example.taskmaster.data.mappers.toEntity
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.domain.models.AccessToken
import com.example.taskmaster.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl constructor(
    private val apiService: ApiService,
    private val accessTokenDao: AccessTokenDao
) : AuthRepository {

    override suspend fun fetchUserToken(login: String, password: String): AccessTokenDto? {
        val accessTokenDto = apiService.fetchUserToken(login, password)
        if(accessTokenDto != null){
            val responseEntity = accessTokenDto?.toEntity()
            saveUserToken(accessToken = responseEntity!!)
        }
        return accessTokenDto ?: null
    }

    override suspend fun getUserToken(): Flow<AccessToken?> {
        return accessTokenDao.getToken.map { it?.toDomain()  }
    }

    override suspend fun saveUserToken(accessToken: AccessTokenEntity) =
        accessTokenDao.saveToken(accessTokenEntity = accessToken)

    override suspend fun deleteUserToken() = accessTokenDao.deleteToken()
}
