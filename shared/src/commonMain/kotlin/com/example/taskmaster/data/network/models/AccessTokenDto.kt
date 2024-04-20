package com.example.taskmaster.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenDto(
    @SerialName("tokenLong")
    var tokenLong: String? = null,
)
