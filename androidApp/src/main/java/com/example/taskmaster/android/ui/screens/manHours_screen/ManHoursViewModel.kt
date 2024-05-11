package com.example.taskmaster.android.ui.screens.manHours_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.ManHoursDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ManHoursViewModel constructor( private val apiService: ApiService, private val taskViewModel: TaskViewModel) : ViewModel()  {
    fun createManHours(manHour: ManHoursDTO, taskId: Int, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                callback(apiService.createManHours(manHour, taskId))
            } catch(e: Exception) {
                println("Exception in ${e}")
            }
        }
    }

    private val _state = mutableStateOf(ItemStates())
    val state: State<ItemStates> = _state

    // Функция для получение списка трудозатрат
    fun getManHours(taskId: Int) {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val data = apiService.fetchManHours(taskId)
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
        val itemState: MutableList<ManHoursDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}