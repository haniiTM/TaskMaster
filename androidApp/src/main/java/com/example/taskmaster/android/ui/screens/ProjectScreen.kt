package com.example.taskmaster.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.RefreshableScreen
import com.example.taskmaster.android.ui.component.StateObject.RoleObject
import com.example.taskmaster.android.ui.component.commonTemplate.Header
import com.example.taskmaster.android.ui.component.commonTemplate.NotificationTemplate
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.component.projectTemplate.ProjectCard
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun ProjectScreen(
    navController: NavController,
    viewModel: TaskViewModel = getViewModel(),
    viewModelURP: UserroleprojectViewModel = getViewModel(),
    result: Boolean
) {
    var isRefreshing by remember { mutableStateOf(false) }

    suspend fun refresh() {
        isRefreshing = true
        delay(2000)
        viewModel.getProject()
        isRefreshing = false
    }

    var showNotification by remember {
        mutableStateOf(true)
    }
    var searchText by remember { mutableStateOf("") }
    var showSearchLine by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        viewModel.getProject()
        viewModelURP.getNotification()
        showNotification = true
    }

    val notificationTaskList = viewModelURP.stateNotification.value.itemState
    val projects = viewModel.state.value.itemProjectState
    val filteredProjects = remember(searchText, projects) {
        derivedStateOf {
            projects.reversed().filter { project ->
                project!!.projectTitle.contains(searchText, ignoreCase = true)
            }
        }
    }

    RefreshableScreen(
        isRefreshing = isRefreshing,
        onRefresh = { refresh() }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Header(
                projectScreenKey = true,
                text = "Все проекты",
                actionIcons = listOf(
                    R.drawable.add_user_icon,
                    R.drawable.remove_user_icon,
                    R.drawable.add_project_icon,
                    R.drawable.exit_icon,
                ),
                navController = navController,
                actionTitle = listOf(
                    "Добавить пользователя",
                    "Удалить пользователя",
                    "Добавить проект",
                    "Выйти"
                ),
                activeMenu = true,
                onShowSearchLineChange = { showSearchLine = !showSearchLine }
            )
            if (showSearchLine) {
                Row(
                    modifier = Modifier.padding(start = 14.dp, end = 14.dp, bottom = 10.dp)
                ) {
                    UnifiedTextBox(
                        value = searchText,
                        onValueChange = { newValue -> searchText = newValue },
                        roundedDownLeftAngle = 15,
                        roundedDownRightAngle = 15,
                        roundedTopRightAngle = 15,
                        roundedTopLeftAngle = 15,
                        borderWidth = 1,
                        placeholder = "Поиск",
                        icon = R.drawable.clear_icon,
                        clearUnit = { searchText = "" }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.padding(horizontal = 14.dp), state = rememberLazyListState()
            ) {
                itemsIndexed(filteredProjects.value) { _, item ->
                    if (item != null) {
                        ProjectCard(item = item, navController = navController)
                    }
                }
            }
        }
        if (!notificationTaskList.isEmpty() && !RoleObject.PMOrAdmin && showNotification){
            Dialog(onDismissRequest = { showNotification = false }) {
                NotificationTemplate(notificationTaskList, navController)
            }
        }
    }
}
