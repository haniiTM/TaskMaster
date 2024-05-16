package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class CalendarPlan(
    val nameTask: String,
    val execution: Int, // Количество дней на выполнение задачи
    var start: Int = 0 // С какого дня начинается выполнение задачи (отображает зависимость задачи)
)