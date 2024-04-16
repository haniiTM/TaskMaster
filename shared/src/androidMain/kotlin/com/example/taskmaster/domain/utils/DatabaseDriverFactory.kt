package com.example.taskmaster.domain.utils

import app.cash.sqldelight.db.SqlDriver
import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.taskmaster.core.database.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(schema = AppDatabase.Schema, context, name = "TaskMaster.db")
    }
}