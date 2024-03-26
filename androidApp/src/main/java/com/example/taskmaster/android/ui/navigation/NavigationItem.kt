package com.example.taskmaster.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.taskmaster.android.R

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {
    object Login : NavigationItem("login", R.string.title_login, null)
}
