package com.example.taskmaster.android.ui.screens.type_of_activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import kotlinx.coroutines.launch

class TypeOfActivityViewModel constructor( private val apiService: ApiService) : ViewModel() {
    private val _state = mutableStateOf(ItemStates())
    val state: State<ItemStates> = _state

    // Функция для получения видов активностей
    fun getTypeActivity() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val data = apiService.fetchTypeOfActivity()
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
        val itemState: MutableList<TypeOfActivityDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}