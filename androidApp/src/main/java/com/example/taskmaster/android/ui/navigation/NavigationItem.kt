package com.example.taskmaster.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.taskmaster.android.R

const val PROJECT_TASK_ARGUMENT_KEY = "id"
sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {
    data object Auth : NavigationItem("auth", R.string.title_auth, null)
    data object Projects : NavigationItem("projects", R.string.title_projects, null)
    data object ProjectTask : NavigationItem("projectTask/{$PROJECT_TASK_ARGUMENT_KEY}", R.string.title_project_task, null) {
        fun passId(id: Int): String {
            return route.replace("{$PROJECT_TASK_ARGUMENT_KEY}", id.toString())
        }
    }
    data object ProjectSubTask : NavigationItem("projectSubTask", R.string.title_project_subtask, null)
    data object TaskInfo : NavigationItem("taskInfo", R.string.title_task_info, null)
    data object TaskLaborCostListScreen : NavigationItem("taskLaborCostList", R.string.title_task_labor_cost_list, null)
    data object AttachmentsListScreen : NavigationItem("attachmentsList", R.string.title_task_labor_cost_list, null)
    data object CalculationOfLaborCosts : NavigationItem("CalculationOfLaborCosts", R.string.title_calculation_of_labor_costs, null)
}