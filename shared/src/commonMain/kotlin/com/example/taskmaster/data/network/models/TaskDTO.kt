package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class TaskDTO(
    var id: Int?,
    var name: String?,
    var status: Int?,
    val start_date: String?,
    var scope: Int?,
    var description: Int?,
    var parent: Int?,
    var userCount: Int?,
    var generation : Int? = 1,
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
        description = null,
        parent = null,
        userCount = null,
        generation = 1,
        content = null,
        typeofactivityid = null,
        position = null,
    )
}
