package com.example.taskmaster.android.ui.screens.status_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.StatusDTO
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import kotlinx.coroutines.launch

class StatusViewModel constructor(private val apiService: ApiService) : ViewModel() {
    private val _state = mutableStateOf(ItemStates())
    val state: State<ItemStates> = _state

    // Функция для получения видов активностей
    fun getStatus() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val data = apiService.fetchStatus()
                _state.value = state.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class ItemStates (
        val itemState: MutableList<StatusDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}