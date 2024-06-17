package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NotificationTask(
    val taskId: Int? = null,
    val taskName: String? = null,
)

@Serializable
data class NotificationItem(
    val listTask: MutableList<NotificationTask> = mutableListOf(),
    val projectName: String? = null
)