package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class TaskByID(
    var id: Int? = null,
    var name: String? = null,
    var status: Int? = null,
    val start_date: String? = null,
    val spentTime: Int? = null, // Сколько часов сотрудник может выделить на задачу
    var score: Int? = null, // Оценка времени
    val spentedTime: String? = null, // Сколько часов сотрудник выделил на задачу
    var userCount: Int? = null,
    var typeofactivityid: Int? = null,
)