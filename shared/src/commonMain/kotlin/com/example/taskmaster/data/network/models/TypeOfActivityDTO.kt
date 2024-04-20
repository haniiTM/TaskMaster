package com.example.taskmaster.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeOfActivityDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)