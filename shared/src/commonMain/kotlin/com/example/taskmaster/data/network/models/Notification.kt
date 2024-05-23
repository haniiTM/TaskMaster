package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskId: Int? = null,
    val taskName: String? = null,
)
@Serializable
data class Notification (
    val listTask: MutableList<Task> = mutableListOf(),
    val projectName: String? = null
)