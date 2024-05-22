package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.screens.newUser_screen.NewUserViewModel
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun UserCard(
    checkBoxAble: Boolean,
    actionButton: Boolean,
    item: String,
    isSelected: Boolean,
    onCheckChanged: (Boolean) -> Unit,
    onDelete: () -> Unit,
    projectId: Int = 0,
    taskId: Int = 0,
    personId: Int,
    viewModel: NewUserViewModel = getViewModel(),
    viewTaskModel: TaskViewModel = getViewModel(),
    triggerRefresh: ((Boolean) -> Unit)? = null,
    role: String? = "",
) {
    var paddingValue = 12
    var expanded by remember { mutableStateOf(false) }
    var showNotification by remember { mutableStateOf(false) }

    Divider(
        color = MaterialTheme.colorScheme.outline,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )

    Box(
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(.7f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    content = {
                        if (checkBoxAble) {
                            paddingValue = 0
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = onCheckChanged,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.Transparent,
                                    checkmarkColor = Color.Black,
                                    uncheckedColor = Color.Black
                                )
                            )
                        }
                        if (!checkBoxAble) {
                            Icon(
                                painter = painterResource(id = getIconForRole(role)),
                                contentDescription = "role_icon",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(start = paddingValue.dp)
                                    .size(25.dp)
                            )
                        }
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(end = 10.dp).fillMaxWidth()) {
                            Text(
                                text = item,
                                modifier = Modifier
                                    .padding(start = 9.dp),
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Black
                            )
                            if (checkBoxAble) {
                                Icon(
                                    painter = painterResource(id = getIconForRole(role)),
                                    contentDescription = "role_icon",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(start = paddingValue.dp)
                                        .size(25.dp)
                                )
                            }
                        }

                    })
            }
            if (showNotification) {
                Dialog(onDismissRequest = { showNotification = !showNotification }) {
                    ActionNotificationTemplate(
                        onDismissRequest = { showNotification = !showNotification },
                        onConfirmation = { },
                        title = "Удалить пользователя"
                    )
                }
            }
            if (actionButton) {
                DropdownMenuArea(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = "menu_icon",
                            tint = Color.Black
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Удалить", color = Color.Red) },
                                onClick = {
                                    onDelete()
                                    expanded = !expanded
                                    if(taskId != 0) {
                                        viewModel.removePersonFromTask(
                                            taskId = taskId,
                                            personId = personId
                                        ) { success ->
                                            // Вызов коллбэка после обновления данных
                                            if (triggerRefresh != null && success) {
                                                viewTaskModel.dataTaskById(taskId!!)
                                                triggerRefresh(success)
                                            }
                                        }
                                    } else {
                                        viewModel.removePersonFromProject(
                                            projectId = projectId,
                                            personId = personId
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.delete_icon),
                                        contentDescription = "delete_icon",
                                        tint = Color.Red
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuArea(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    var localExpanded by remember { mutableStateOf(expanded) }

    Box {
        content()

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp, top = 8.dp)
        ) {

        }
        DisposableEffect(localExpanded) {
            onDispose {
                onExpandedChange(localExpanded)
            }
        }
    }
}

fun getIconForRole(role: String?): Int {
    return when (role) {
        "Backend" -> R.drawable.backend_role_icon
        "Frontend" -> R.drawable.frontend_role_icon
        "Тестирование" -> R.drawable.tester_role_icon
        "UI/UX дизайн" -> R.drawable.designer_role_icon
        "Проектный менеджмент" -> R.drawable.project_manager_role_icon
        "Админ" -> R.drawable.administrator_role_icon
        else -> R.drawable.warning
    }
}