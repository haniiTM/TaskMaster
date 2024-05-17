package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class CalendarPlan(
    val taskId: Int,
    val nameTask: String,
    val execution_date: MutableList<String>,
    val haveExecuter: Boolean
)