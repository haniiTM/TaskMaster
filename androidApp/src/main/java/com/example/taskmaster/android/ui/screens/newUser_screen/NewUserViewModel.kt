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

    // Функция для получения всех проектов
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

    // Получение всех сотрудников привязанных к задаче
    data class ItemStates (
        val itemState: MutableList<PersonDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    private val _stateInTask = mutableStateOf(ItemStatesInTask())
    val stateInTask: State<ItemStatesInTask> = _stateInTask

    fun getPersonInTask(taskId: Int) {
        viewModelScope.launch {
            try {
                _stateInTask.value = stateInTask.value.copy(isLoading = true)
                val data = apiService.fetchPersonInTask(taskId)
                _stateInTask.value = stateInTask.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateInTask.value = stateInTask.value.copy(isLoading = false)
            }
        }
    }
    data class ItemStatesInTask (
        val itemState: MutableList<PersonDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    // Получение всех сотрудников привязанных к проекту
    private val _stateInProject = mutableStateOf(ItemStatesInProject())
    val stateInProject: State<ItemStatesInProject> = _stateInProject

    fun getPersonInProject(projId: Int) {
        viewModelScope.launch {
            try {
                _stateInProject.value = stateInProject.value.copy(isLoading = true)
                val data = apiService.fetchPersonInProject(projId)
                _stateInProject.value = stateInProject.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateInProject.value = stateInProject.value.copy(isLoading = false)
            }
        }
    }
    data class ItemStatesInProject (
        val itemState: MutableList<PersonDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    // Извлечение всех пользователей, которые еще не были добавлены в проект
    private val _stateFreeFromProject = mutableStateOf(ItemStatesFreeFromProject())
    val stateFreeFromProject: State<ItemStatesFreeFromProject> = _stateFreeFromProject

    fun getPersonFreeFromProject(projId: Int) {
        viewModelScope.launch {
            try {
                _stateFreeFromProject.value = stateFreeFromProject.value.copy(isLoading = true)
                val data = apiService.fetchPersonFreeFromProject(projId)
                _stateFreeFromProject.value = stateFreeFromProject.value.copy(
                    itemState = data,
                    isLoading = false
                )
            } catch(e: Exception) {
                _stateFreeFromProject.value = stateFreeFromProject.value.copy(isLoading = false)
            }
        }
    }
    data class ItemStatesFreeFromProject (
        val itemState: MutableList<PersonDTO?> = mutableListOf(),
        val isLoading: Boolean = false
    )

    fun deletePerson(personId: MutableList<Int>){
        viewModelScope.launch {
            try {
                apiService.deletePersonFromSystem(personId)
            } catch(e: Exception) {
                println("Exception in deletePerson ${e}")
            } finally {
                getAllPerson()
            }
        }
    }
}