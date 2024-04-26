package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login: String,
    val password: String,
    val fio: String,
    val role: String
)