package com.example.taskmaster.data.cache.sqldelight

import app.cash.sqldelight.coroutines.asFlow
import com.example.taskmaster.core.database.AccessTokenEntity
import com.example.taskmaster.core.database.AppDatabaseQueries
import com.example.taskmaster.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccessTokenDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    val appDatabase = AppDatabaseQueries(databaseDriverFactory.createDriver())

    /**
     * Save access token returned from network call to SQLDelight database
     */

    fun saveToken(
        accessTokenEntity: AccessTokenEntity
    ) {
        appDatabase.transaction {
            appDatabase.saveToken(accessTokenEntity)
        }
    }

    /**
     * Returns all data store in access token entity table in SQLDelight database
     * as a flow
     */
    val getToken: Flow<AccessTokenEntity?> = appDatabase.getToken().asFlow().map {
        it.executeAsOneOrNull()
    }
    /**
     * Deletes all data in access token entity table in SQLDelight database
     */
    fun deleteToken() = appDatabase.deleteToken()
}
