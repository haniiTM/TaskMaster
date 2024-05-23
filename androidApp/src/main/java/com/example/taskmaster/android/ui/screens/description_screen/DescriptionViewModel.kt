package com.example.taskmaster.android.ui.screens.description_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.DescriptionDTOFileDTO
import com.example.taskmaster.data.network.models.ManHoursDTO
import kotlinx.coroutines.launch

class DescriptionViewModel constructor( private val apiService: ApiService) : ViewModel() {
    private val _state = mutableStateOf(DescriptionViewModel.ItemDescription())
    val state: State<DescriptionViewModel.ItemDescription> = _state

    // Функция описания
    fun getDescription(descrId: Int) {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val descriptionData = apiService.listFileInTask(descrId)
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
        val itemState: DescriptionDTOFileDTO? = null,
        val isLoading: Boolean = false
    )

    fun uploadFile(fileName: String, taskId: Int, data: ByteArray) {
        viewModelScope.launch {
            try {
                apiService.sendFile(fileName, taskId, data)
                getDescription(taskId)
            } catch(e: Exception) {
                println("Exception in ${e}")
            }
        }
    }
}