package com.example.taskmaster.android.ui.screens.newUser_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.data.network.ApiService
import com.example.taskmaster.data.network.models.RegisterReceiveRemote
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
}