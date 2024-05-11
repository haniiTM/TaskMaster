package com.example.taskmaster.android.ui.screens.userroleproject_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

class UserroleprojectViewModel constructor(private val apiService: ApiService) : ViewModel() {
    fun linkUserToTaskOrProject(urp: UserRoleProjectDTO, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                callback(apiService.linkUserTaskOrProject(urp))
            } catch(e: Exception) {
                println("Exception in link user to task or project $e")
            }
        }
    }

    fun changeHoursSpent(urp: UserRoleProjectDTO, taskId: Int, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                callback(apiService.updateHoursSpent(urp, taskId))
            } catch(e: Exception) {
                println("Exception in link user to task or project $e")
            }
        }
    }
}