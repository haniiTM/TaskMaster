package com.example.taskmaster.android.ui.screens.newUser_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.PersonDTO
import com.example.taskmaster.data.network.models.RegisterReceiveRemote
import com.example.taskmaster.data.network.models.TypeOfActivityDTO
import kotlinx.coroutines.launch

class NewUserViewModel constructor( private val apiService: ApiService) : ViewModel() {
    // Функция cоздания нового пользоветеля
    fun createNewUser(firstName: String, lastName: String, login: String, password: String, role: String) {
        viewModelScope.launch {
            try {
                val user = RegisterReceiveRemote(
                    fio = "${lastName} ${firstName}",
                    login = login,
                    password = password,
                    role = role
                )
                apiService.registerUser(user)
            } catch(e: Exception) {
                println("Exception in createNewUser ${e}")
            }
        }
    }

    private val _state = mutableStateOf(ItemStates())
    val state: State<ItemStates> = _state

    // Функция для получения видов активностей
    fun getAllPerson() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                val data = apiService.fetchAllPerson()
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
        val itemState: MutableList<PersonDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )
}