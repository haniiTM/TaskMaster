package com.example.taskmaster.domain.use_cases

import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.domain.repositories.AuthRepository
import org.koin.core.component.KoinComponent

class GetAccessTokenDtoUseCase(private val repository: AuthRepository) : KoinComponent {
    suspend fun fetchUserToken(login: String, password: String): AccessTokenDto? {
        return repository.fetchUserToken(login, password)
    }
}