package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ActivityDTO(
    val id: Int? = null,
    val name: String? = null
)