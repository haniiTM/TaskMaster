package com.example.taskmaster.data.mappers

import com.example.taskmaster.core.database.AccessTokenEntity
import com.example.taskmaster.data.network.models.AccessTokenDto

internal fun AccessTokenDto.toEntity(): AccessTokenEntity {
    return AccessTokenEntity(
        id = 0,
        tokenLong = this.tokenLong
    )
}