package com.example.taskmaster.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.taskmaster.android.R

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {
    object Auth : NavigationItem("auth", R.string.title_auth, null)
    object Projects : NavigationItem("projects", R.string.title_projects, null)
    object ProjectTask : NavigationItem("projectTask", R.string.title_project_task, null)
    object ProjectSubTask : NavigationItem("projectSubTask", R.string.title_project_subtask, null)

}