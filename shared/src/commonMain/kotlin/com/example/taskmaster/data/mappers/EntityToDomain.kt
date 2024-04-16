package com.example.taskmaster.data.mappers

import com.example.taskmaster.core.database.AccessTokenEntity
import com.example.taskmaster.domain.models.AccessToken

internal fun AccessTokenEntity.toDomain(): AccessToken {
    return AccessToken(
        tokenLong = this.tokenLong,
    )
}
