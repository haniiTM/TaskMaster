package com.example.taskmaster.android.ui.screens.description_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.mappers.toDomain
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.DescriptionDTO
import kotlinx.coroutines.launch

class DescriptionViewModel constructor( private val apiService: ApiService) : ViewModel() {
    private val _state = mutableStateOf(DescriptionViewModel.ItemDescription())
    val state: State<DescriptionViewModel.ItemDescription> = _state

    // Функция описания
    fun getDescription(descrId: Int) {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val descriptionData = apiService.fetchDescription(descrId)
                _state.value = state.value.copy(
                    itemState = descriptionData,
                    isLoading = false
                )
            } catch(e: Exception) {
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class ItemDescription (
        val itemState: MutableList<DescriptionDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}