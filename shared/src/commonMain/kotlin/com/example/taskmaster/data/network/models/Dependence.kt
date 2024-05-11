package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Dependence (
    val dependsOn: MutableList<Int> = mutableListOf(),
    val dependent: Int = 0,
)