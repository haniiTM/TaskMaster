package com.example.taskmaster.android.ui.screens.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.android.ui.screens.description_screen.DescriptionViewModel
import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.DescriptionDTO
import com.example.taskmaster.domain.repositories.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {
    suspend fun fetchUserToken(login: String, password: String): AccessTokenDto {
        return authRepository.fetchUserToken(login, password)
    }

    fun dataToken(login: String, password: String): LiveData<AccessTokenDto> {
        val resultLiveData = MutableLiveData<AccessTokenDto>()
        viewModelScope.launch {
            val success = fetchUserToken(login, password)
            resultLiveData.value = success
        }

        return resultLiveData
    }

}