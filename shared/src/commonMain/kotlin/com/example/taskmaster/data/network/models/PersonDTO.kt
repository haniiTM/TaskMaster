package com.example.taskmaster.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class PersonDTO (
    val id:Int?,
    var surname: String,
    var name:String,
    var patronymic: String?,
    var role: String?,
)