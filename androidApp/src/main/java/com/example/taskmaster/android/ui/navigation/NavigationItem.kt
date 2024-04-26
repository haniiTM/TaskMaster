package com.example.taskmaster.android.ui.navigation

import androidx.annotation.DrawableRes

const val PROJECT_TASK_ARGUMENT_KEY = "id"
const val PROJECT_TITLE_ARGUMENT_KEY = "projectTitle"
const val TASK_TITLE_ARGUMENT_KEY = "name"
const val PROJECT_SUBTASK_ARGUMENT_KEY = "id"
const val PROJECT_INFO_ARGUMENT_KEY = "id"
const val TASK_DESCRIPTION_ARGUMENT_KEY = "description"
const val USER_ROLE_ARGUMENT_KEY = "result"
sealed class NavigationItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int?
) {
    data object Auth : NavigationItem("auth", "R.string.title_auth", null)
    data object Projects : NavigationItem("projects/{$USER_ROLE_ARGUMENT_KEY}", "R.string.title_auth", null){
        fun passIdAndTitle(success: Boolean): String {
            return "projects/$success"
        }
    }
    data object ProjectTask : NavigationItem("projectTask/{$PROJECT_TASK_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null) {
        fun passIdAndTitle(id: Int, title: String): String {
            return "projectTask/$id/$title"
        }
    }
    data object ProjectSubTask : NavigationItem("projectSubTask/{$PROJECT_SUBTASK_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}/{$TASK_TITLE_ARGUMENT_KEY}/{$TASK_DESCRIPTION_ARGUMENT_KEY}", "R.string.title_auth", null) {
        fun passIdAndTitle(id: Int, title: String, taskTitle: String, taskDescription: String): String {
            return "projectSubTask/$id/$title/$taskTitle/$taskDescription"
        }
    }
    data object TaskInfo : NavigationItem("taskInfo/{$PROJECT_INFO_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null){
        fun passIdAndTitle(id: Int, title: String): String {
            return "taskInfo/$id/$title"
        }
    }
    data object TaskLaborCostListScreen : NavigationItem("taskLaborCostList/{$PROJECT_INFO_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null){
        fun passIdAndTitle(id: Int, title: String): String {
            return "taskLaborCostList/$id/$title"
        }
    }
    data object AttachmentsListScreen : NavigationItem("attachmentsList/{$PROJECT_TASK_ARGUMENT_KEY}/{$PROJECT_TITLE_ARGUMENT_KEY}", "R.string.title_auth", null){
        fun passIdAndTitle(id: Int, title: String): String {
            return "attachmentsList/$id/$title"
        }
    }
    data object CalculationOfLaborCosts : NavigationItem("CalculationOfLaborCosts", "R.string.title_auth", null)
}