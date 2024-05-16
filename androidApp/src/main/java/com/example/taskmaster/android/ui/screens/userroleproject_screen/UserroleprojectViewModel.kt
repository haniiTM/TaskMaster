package com.example.taskmaster.android.ui.screens.userroleproject_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.CalendarPlan
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
import kotlinx.coroutines.launch

class UserroleprojectViewModel constructor(private val apiService: ApiService) : ViewModel() {
    // Привязка пользователя к проекту
    fun linkUserToTaskOrProject(urp: UserRoleProjectDTO, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                callback(apiService.linkUserTaskOrProject(urp))
            } catch(e: Exception) {
                println("Exception in link user to task or project $e")
            }
        }
    }

    // Смена кол-во часов, которые пользователь может выделять в день
    fun changeHoursSpent(urp: UserRoleProjectDTO, taskId: Int, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                callback(apiService.updateHoursSpent(urp, taskId))
            } catch(e: Exception) {
                println("Exception in link user to task or project $e")
            }
        }
    }

    private val _state = mutableStateOf(ItemStates())
    val state: State<ItemStates> = _state

    // Получение отчета по календарному плану за определенный проект
    fun getCalendarPlan(projectId: Int) {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val data = apiService.fetchCalenderPlan(projectId)
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
        val itemState: MutableList<CalendarPlan?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}