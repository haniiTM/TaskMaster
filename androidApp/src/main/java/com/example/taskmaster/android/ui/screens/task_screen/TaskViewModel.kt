package com.example.taskmaster.android.ui.screens.task_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.mappers.toDomain
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.domain.models.ItemProjectState
import kotlinx.coroutines.launch


class TaskViewModel constructor( private val apiService: ApiService) : ViewModel()
{
    private val _state = mutableStateOf(ItemProjectStates())
    val state: State<ItemProjectStates> = _state

    // Функция для получения проектов
    fun getProject() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val data = apiService.fetchProject()
                val listItemProjectStates: MutableList<ItemProjectState?> = mutableListOf()
                for (item in data) {
                    if (item != null) {
                        listItemProjectStates.add(item.toDomain())
                    }
                }
                _state.value = state.value.copy(
                    itemProjectState = listItemProjectStates,
                    isLoading = false
                )
            } catch(e: Exception) {
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    private val _stateTask = mutableStateOf(ItemTaskStates())
    val stateTask: State<ItemTaskStates> = _stateTask

    // Функция для получения задач/подзадач
    fun getTask(idProj: Number) {
        viewModelScope.launch {
            try {
                _stateTask.value = stateTask.value.copy(isLoading = true)
                val data = apiService.fetchTask(idProj)
                _stateTask.value = stateTask.value.copy(
                    itemTaskState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateTask.value = stateTask.value.copy(isLoading = false)
            }
        }
    }

    // Функция для получения задач/подзадач
    fun createProject(nameProject: String) {
        viewModelScope.launch {
            try {
                apiService.createProject(nameProject)
            } catch(e: Exception) {
                println("Exception in createProject ${e}")
            }
            getProject()
        }
    }

    data class ItemTaskStates (
        val itemTaskState: MutableList<TaskDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    data class ItemProjectStates (
        val itemProjectState: MutableList<ItemProjectState?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}