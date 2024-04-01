package com.example.taskmaster.android.ui.activity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.domain.repositories.AuthRepository
import com.example.taskmaster.domain.models.AccessToken
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainActivityViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _tokenLong: MutableState<AccessToken?> = mutableStateOf(null)
    val accessToken: State<AccessToken?> get() = _tokenLong

    init {
        getUserToken()
    }

    private fun getUserToken() {
        viewModelScope.launch {
            try {
                val tok = authRepository.fetchUserToken("admin", "admin123")
                Napier.e("tok: ${tok}")



                val response = authRepository.getUserToken()
                response.collectLatest {
                    _tokenLong.value = it
                }

            } catch (e: Exception) {
                Napier.e("Error getting token: ${e.localizedMessage}")
            }
        }
    }
}
