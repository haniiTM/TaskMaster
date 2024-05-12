package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Dependence (
    val dependsOn: Int = 0,
    val dependent: Int = 0,
)