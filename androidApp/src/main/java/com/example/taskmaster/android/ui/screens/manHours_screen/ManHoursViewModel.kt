package com.example.taskmaster.android.ui.screens.manHours_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.ManHoursDTO
import com.example.taskmaster.data.network.models.ManHoursReportDTO
import kotlinx.coroutines.launch


class ManHoursViewModel constructor( private val apiService: ApiService) : ViewModel()  {
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


    private val _stateManHoursReport = mutableStateOf(ItemStatesManHoursReport())
    val stateManHoursReport: State<ItemStatesManHoursReport> = _stateManHoursReport

    // Получение отчета по трудозатратам
    fun getReportManHours(projectId: Int) {
        viewModelScope.launch {
            try {
                _stateManHoursReport.value = stateManHoursReport.value.copy(isLoading = true)
                val data = apiService.fetchReportManHours(projectId)
                _stateManHoursReport.value = stateManHoursReport.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch (e: Exception) {
                _stateManHoursReport.value = stateManHoursReport.value.copy(isLoading = false)
            }
        }
    }

    data class ItemStatesManHoursReport(
        val itemState: MutableList<ManHoursReportDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    private val _stateFileManHours = mutableStateOf(ItemStatesFile())
    val stateFileManHours: State<ItemStatesFile> = _stateFileManHours

    fun fetchFileForManHours(projectId: Int) {
        viewModelScope.launch {
            try {
                _stateFileManHours.value = stateFileManHours.value.copy(isLoading = true)
                val data = apiService.downloadFileForManHours(projectId)
                _stateFileManHours.value = stateFileManHours.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateFileManHours.value = stateFileManHours.value.copy(isLoading = false)
            }
        }
    }

    data class ItemStatesFile (
        val itemState:  String? = null,
        val isLoading: Boolean = false
    )

    fun updateManHours(id: Int, comment: String, taskId: Int) {
        viewModelScope.launch {
            try {
                apiService.updateManHours(id, comment)
                getManHours(taskId)
            } catch(e: Exception) {
                println("Exception in ${e}")
            }
        }
    }
}