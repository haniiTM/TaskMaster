package com.example.taskmaster.android.ui.screens.activity_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.ActivityDTO
import kotlinx.coroutines.launch

class ActivityViewModel constructor( private val apiService: ApiService) : ViewModel() {
    private val _state = mutableStateOf(ItemStates())
    val state: State<ItemStates> = _state

    fun getActivity() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val data = apiService.fetchActivity()
                _state.value = state.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class ItemStates(
        val itemState: MutableList<ActivityDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}