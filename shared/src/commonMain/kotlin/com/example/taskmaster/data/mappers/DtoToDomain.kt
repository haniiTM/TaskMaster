package com.example.taskmaster.data.mappers

import com.example.taskmaster.data.network.models.*
import com.example.taskmaster.domain.models.*

internal fun TaskDTO.toDomain(): Task {
    return Task(
        id = this.id,
        name = this.name,
        status = this.status,
        start_date = this.start_date,
        scope = this.scope,
        description = this.description,
        parent = this.parent,
        userCount = this.userCount,
        generation = this.generation,
        typeofactivityid = this.typeofactivityid,
        position = this.position,
        gruop = this.gruop,
        dependence = this.dependence
    )
}