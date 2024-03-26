package com.example.taskmaster.android.ui.screens.login_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage: MutableState<String> = mutableStateOf("Unknown error")
    val errorMessage: State<String> get() = _errorMessage

    private val _task = MutableLiveData<TaskDTO?>()

    fun fetchUserToken(login: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                authRepository.fetchUserToken(login, password)
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
        _isLoading.value = false
    }

    fun getTask() {
        viewModelScope.launch {
            try {
                val response = authRepository.fetchTask()
                response.collectLatest {
                    _task.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }
}