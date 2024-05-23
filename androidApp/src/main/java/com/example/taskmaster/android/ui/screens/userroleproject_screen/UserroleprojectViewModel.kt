package com.example.taskmaster.android.ui.screens.userroleproject_screen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.CalendarPlan
import com.example.taskmaster.data.network.models.Notification
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
import kotlinx.coroutines.launch
import java.io.File

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

    private val _stateFile = mutableStateOf(ItemStatesFile())
    val stateFile: State<ItemStatesFile> = _stateFile

    fun fetchFile(projectId: Int) {
        viewModelScope.launch {
            try {
                _stateFile.value = stateFile.value.copy(isLoading = true)
                val data = apiService.downloadFile(projectId)
                _stateFile.value = stateFile.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateFile.value = stateFile.value.copy(isLoading = false)
            }
        }
    }

    data class ItemStatesFile (
        val itemState:  String? = null,
        val isLoading: Boolean = false
    )

    private val _stateNotification = mutableStateOf(ItemStatesNotification())
    val stateNotification: State<ItemStatesNotification> = _stateNotification

    // Получение отчета по календарному плану за определенный проект
    fun getNotification() {
        viewModelScope.launch {
            try {
                _stateNotification.value = stateNotification.value.copy(isLoading = true)
                val data = apiService.getNotification()
                _stateNotification.value = stateNotification.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateNotification.value = stateNotification.value.copy(isLoading = false)
            }
        }
    }

    data class ItemStatesNotification (
        val itemState: MutableList<Notification?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}