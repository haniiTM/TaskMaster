package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ManHoursReportDTO(
    val id: Int,
    val createdAt: String?,
    val hoursSpent: String?,
    val taskName: String
)