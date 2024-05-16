package com.example.taskmaster.domain.utils

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.taskmaster.core.database.AppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = AppDatabase.Schema, name = "TaskMaster.db")
    }
}