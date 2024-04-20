package com.example.taskmaster.android.ui.navigation

import androidx.annotation.DrawableRes

const val PROJECT_TASK_ARGUMENT_KEY = "id"
const val PROJECT_TITLE_ARGUMENT_KEY = "projectTitle"
const val PROJECT_SUBTASK_ARGUMENT_KEY = "id"
sealed class NavigationItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int?
) {
    data object Auth : NavigationItem("auth", "R.string.title_auth", null)
    data object Projects : NavigationItem("projects", "R.string.title_auth", null)
    data object ProjectTask : NavigationItem("projectTask/{$PROJECT_TASK_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null) {
        fun passIdAndTitle(id: Int, title: String): String {
            return "projectTask/$id/$title"
        }
    }
    data object ProjectSubTask : NavigationItem("projectSubTask/{$PROJECT_TASK_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null) {
        fun passIdAndTitle(id: Int, title: String): String {
            return "projectSubTask/$id/$title"
        }
    }
    data object TaskInfo : NavigationItem("taskInfo/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null){
        fun passIdAndTitle(title: String): String {
            return "projectSubTask/$title"
        }
    }
    data object TaskLaborCostListScreen : NavigationItem("taskLaborCostList", "R.string.title_auth", null)
    data object AttachmentsListScreen : NavigationItem("attachmentsList/{$PROJECT_TASK_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null){
        fun passIdAndTitle(id: Int, title: String): String {
            return "attachmentsList/$id/$title"
        }
    }
    data object CalculationOfLaborCosts : NavigationItem("CalculationOfLaborCosts", "R.string.title_auth", null)
}