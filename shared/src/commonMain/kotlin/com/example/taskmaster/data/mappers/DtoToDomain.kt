package com.example.taskmaster.data.mappers

import com.example.taskmaster.data.network.models.*
import com.example.taskmaster.domain.models.*

fun TaskDTO.toDomain(): ItemProjectState {
    return ItemProjectState(
        id = this.id!!,
        projectTitle = this.name,
        projectTimeLeft = this.scope!!,
        projectMemberCount = this.userCount!!,
    )
}