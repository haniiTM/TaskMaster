package com.example.taskmaster.android.ui.screens.login_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.domain.repositories.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {
    suspend fun fetchUserToken(login: String, password: String): Boolean {
        return authRepository.fetchUserToken(login, password)
    }

    fun dataToken(login: String, password: String): LiveData<Boolean> {
        val resultLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val success = fetchUserToken(login, password)
            resultLiveData.value = success
        }

        return resultLiveData
    }
}