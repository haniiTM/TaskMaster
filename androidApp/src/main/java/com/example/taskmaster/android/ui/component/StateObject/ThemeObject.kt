package com.example.taskmaster.android.ui.component.StateObject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AppState {
    var darkTheme by mutableStateOf(false)
}