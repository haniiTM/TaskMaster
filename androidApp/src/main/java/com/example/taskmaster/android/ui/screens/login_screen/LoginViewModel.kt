package com.example.taskmaster.android.ui.screens.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.data.network.models.AccessTokenDto
import com.example.taskmaster.data.network.models.TaskByID
import com.example.taskmaster.domain.repositories.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val _stateAccessToken = MutableLiveData<ItemAccessToken>()
    val stateAccessToken: LiveData<ItemAccessToken> = _stateAccessToken

    suspend fun fetchUserToken(login: String, password: String): AccessTokenDto? {
        return authRepository.fetchUserToken(login, password)
    }

    fun dataToken(login: String?, password: String?): LiveData<AccessTokenDto> {
        val resultLiveData = MutableLiveData<AccessTokenDto>()
        if (login!= null && password!= null && login.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch {
                val success = fetchUserToken(login, password)

                if (success == null){
                    resultLiveData.postValue(AccessTokenDto(tokenLong = ""))
                } else {
                    resultLiveData.postValue(success)
                    _stateAccessToken.postValue(ItemAccessToken(success.tokenLong!!))
                }
                resultLiveData.postValue(success)
            }
        } else {
            resultLiveData.postValue(AccessTokenDto(tokenLong = ""))
        }
        return resultLiveData
    }

    data class ItemAccessToken(
        val itemAccessToken: String
    )
}