package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class TaskDTO(
    var id: Int?,
    var name: String?,
    var status: Int?,
    val start_date: String?,
    var scope: Int?,
    var parent: Int?,
    var userCount: Int? = null,
    var generation : Int? = null,
    var content: String? = null,
    var typeofactivityid: Int?,
    var position: Int?,
    val taskDependenceOn: TaskDependenceOn? = null
)
{
    constructor() : this(
        id = null,
        name = null,
        status = null,
        start_date = null,
        scope = null,
        parent = null,
        userCount = null,
        generation = 1,
        content = null,
        typeofactivityid = null,
        position = null,
    )
}
