package com.example.taskmaster.android.ui.screens.userroleproject_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserroleprojectViewModel constructor( private val apiService: ApiService) : ViewModel() {
    fun linkUserToTaskOrProject(urp: UserRoleProjectDTO) {
        viewModelScope.launch {
            try {
                apiService.linkUserTaskOrProject(urp)
                delay(200) // Задержка необходима чтобы успели подгрузиться данные о задаче
            } catch(e: Exception) {
                println("Exception in link user to task or project ${e}")
            }
        }
    }
}