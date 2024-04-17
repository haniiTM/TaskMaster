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

    private val _stateUnfulfilleddTask = mutableStateOf(ItemUnfulfilleddTaskStates())
    val stateUnfulfilleddTask: State<ItemUnfulfilleddTaskStates> = _stateUnfulfilleddTask

    // Функция для получения не выполненных задач/подзадач
    fun getUnfulfilleddTask(idProj: Number) {
        viewModelScope.launch {
            try {
                _stateUnfulfilleddTask.value = stateUnfulfilleddTask.value.copy(isLoading = true)
                val data = apiService.fetchUnfulfilleddTask(idProj)
                _stateUnfulfilleddTask.value = stateUnfulfilleddTask.value.copy(
                    itemTaskState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateUnfulfilleddTask.value = stateUnfulfilleddTask.value.copy(isLoading = false)
            }
        }
    }

    data class ItemUnfulfilleddTaskStates (
        val itemTaskState: MutableList<TaskDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    private val _stateCompletedTask = mutableStateOf(ItemCompletedTaskStates())
    val stateCompletedTask: State<ItemCompletedTaskStates> = _stateCompletedTask

    // Функция для получения выполненных задач/подзадач
    fun getCompletedTask(idProj: Number) {
        viewModelScope.launch {
            try {
                _stateCompletedTask.value = stateCompletedTask.value.copy(isLoading = true)
                val data = apiService.fetchCompletedTask(idProj)
                _stateCompletedTask.value = stateCompletedTask.value.copy(
                    itemTaskState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateCompletedTask.value = stateCompletedTask.value.copy(isLoading = false)
            }
        }
    }

    data class ItemCompletedTaskStates (
        val itemTaskState: MutableList<TaskDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

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

    data class ItemProjectStates (
        val itemProjectState: MutableList<ItemProjectState?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    // Функция для обновление статуса
    fun updateStatus(taskId: Int, statusId: Int, nameTask: String, parent: Int) {
        viewModelScope.launch {
            try {
                apiService.updateStatusTask(taskId, statusId, nameTask)
            } catch(e: Exception) {
                println("Exception in createProject ${e}")
            }
            getCompletedTask(parent)
            getUnfulfilleddTask(parent)
        }
    }

    // Функция для обновление статуса
    fun createTask(task: TaskDTO, parentId: Int) {
        viewModelScope.launch {
            try {
                apiService.createTask(task, parentId)
            } catch(e: Exception) {
                println("Exception in createProject ${e}")
            }
            getUnfulfilleddTask(parentId)
        }
    }
}