package com.example.taskmaster.domain.models

data class Task(
    var id: Int?,
    val name: String,
    var status: Int?,
    val start_date: String?,
    var scope: Int?,
    var description: Int?,
    var parent: Int?,
    val userCount: Int? = null,
    var generation : Int? = 1,
    val typeofactivityid: Int?,
    var position: Int?,
    var gruop: Int?,
    var dependence: String?
)