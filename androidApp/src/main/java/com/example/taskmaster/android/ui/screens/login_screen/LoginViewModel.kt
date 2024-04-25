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
//    private val _state = mutableStateOf(LoginViewModel.ItemAccessTokenDto())
//    val state: State<LoginViewModel.ItemAccessTokenDto> = _state
//
//    fun dataToken(login: String, password: String) {
//        viewModelScope.launch {
//            try {
//                _state.value = state.value.copy(isLoading = true)
//                val tokenAndFlag = authRepository.fetchUserToken(login, password)
//                _state.value = state.value.copy(
//                    itemState = tokenAndFlag,
//                    isLoading = false
//                )
//            } catch(e: Exception) {
//                _state.value = state.value.copy(isLoading = false)
//            }
//        }
//    }
//
//    data class ItemAccessTokenDto (
//        val itemState: AccessTokenDto? = null,
//        val isLoading: Boolean = false
//    )


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