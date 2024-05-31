package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class ManHoursDTO (
    val id: Int ? = null,
    val created_at: String? = null,
    val hours_spent: String? = null,
    var comment: String? = null,
    var taskid: Int? = null,
    var projectid: Int? = null,
    val activityid: Int? = null,
)