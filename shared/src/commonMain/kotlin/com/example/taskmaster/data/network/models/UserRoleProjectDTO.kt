package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class UserRoleProjectDTO(
    val id: Int? = null,
    var userid: MutableList<Int> = mutableListOf(),
    val projectid: Int? = null,
    val type_of_activityid: Int? = null,
    val score: Int? = null,
    val current_task_id: Int? = null,
    val creater_project: Int? = null
)