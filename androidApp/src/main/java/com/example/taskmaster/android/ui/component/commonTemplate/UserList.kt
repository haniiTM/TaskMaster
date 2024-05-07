package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskmaster.android.ui.screens.newUser_screen.NewUserViewModel
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
import org.koin.androidx.compose.getViewModel

@Composable
fun UserList(
    checkBoxAble: Boolean,
    addRoleButton: Boolean,
    title: String = "",
    buttonText: String,
    id: Int = 0,
    projectId: Int = 0,
    paddingValue: Int = 0,
    showPersonInProject: Boolean, // Флаг для отображение сотрудников в проекте
    viewModel: NewUserViewModel = getViewModel(),
    viewModelURP: UserroleprojectViewModel = getViewModel(),
    viewTaskModel: TaskViewModel = getViewModel(),
    onCloseButtonClick: (() -> Unit)? = null,
    removeUserWindowKey: Boolean
) {
    val selectedUsers = remember { mutableStateOf<Set<Int>>(setOf()) }

    if (id != 0 && !showPersonInProject) {
        // Получение списка сотрудников в задаче
        LaunchedEffect(id != 0 && showPersonInProject) {
            viewModel.getPersonInTask(id)
        }
    } else if (projectId != 0 && showPersonInProject) {
        // Получение списка сотрудников в проекте
        LaunchedEffect(projectId != 0 && showPersonInProject) {
            viewModel.getPersonInProject(projectId)
        }
    } else {
        // Получение списка всех сотрудников
        LaunchedEffect(key1 = true) {
            viewModel.getAllPerson()
        }
    }

    val linearGradient =
        Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onSecondary
            )
        )

    var showWindow by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .padding(horizontal = paddingValue.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15.dp)
            )
            .sizeIn(maxHeight = 250.dp)
    ) {
        Column {
            if (title != "") {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(35.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            LazyColumn(
                state = rememberLazyListState(), modifier = Modifier.sizeIn(maxHeight = 180.dp)
            ) {
                val itemsList = if (id != 0 && !showPersonInProject) {
                    viewModel.stateInTask.value.itemState
                } else if (projectId != 0 && showPersonInProject) {
                    // Получение списка сотрудников в проекте
                    viewModel.stateInProject.value.itemState
                } else {
                    // Получение списка всех сотрудников
                    viewModel.state.value.itemState
                }
                itemsIndexed(itemsList) { _, item ->
                    if (item != null) {
                        val isSelected = selectedUsers.value.contains(item.id!!)
                        UserCard(
                            checkBoxAble = checkBoxAble,
                            addRoleButton = addRoleButton,
                            item = "${item.surname} ${item.name} ${item.patronymic}",
                            isSelected = isSelected,
                            onCheckChanged = { isSelected ->
                                if (isSelected) {
                                    selectedUsers.value += item.id!!
                                } else {
                                    selectedUsers.value -= item.id!!
                                }
                            }
                        )
                    }
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if (!removeUserWindowKey) {
                        if (onCloseButtonClick == null) {
                            showWindow = true
                        } else {
                            onCloseButtonClick()
                            if (selectedUsers.value.isNotEmpty() && id!= 0) {
                                val selectedUsersList = mutableListOf<Int>()
                                selectedUsersList.addAll(selectedUsers.value)

                                viewModelURP.linkUserToTaskOrProject(
                                    UserRoleProjectDTO(
                                        userid = selectedUsersList,
                                        current_task_id = id
                                    )
                                )

                                viewTaskModel.dataTaskById(id!!)
                            }
                        }
                    } else {

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Text(text = buttonText, color = Color.Black)
            }
        }
    }
    if (showWindow && removeUserWindowKey != true) {
        Dialog(onDismissRequest = { showWindow = false }) {
            UserList(
                checkBoxAble = true,
                addRoleButton = false,
                title = "выберите пользователя",
                buttonText = "Добавить",
                paddingValue = 0,
                id = id,
                onCloseButtonClick = { showWindow = false },
                projectId = projectId,
                showPersonInProject = true,
                removeUserWindowKey = false
            )
        }
    }
}