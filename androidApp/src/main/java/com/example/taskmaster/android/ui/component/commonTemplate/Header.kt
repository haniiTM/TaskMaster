package com.example.taskmaster.android.ui.component.commonTemplate

import AppSettings
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.StateObject.AppState
import com.example.taskmaster.android.ui.component.StateObject.RoleObject
import com.example.taskmaster.android.ui.component.popupWindows.NewProjectWindow
import com.example.taskmaster.android.ui.component.popupWindows.NewUserWindow
import com.example.taskmaster.android.ui.component.popupWindows.SearchPopUpWindow
import com.example.taskmaster.android.ui.navigation.NavigationItem

@SuppressLint("SuspiciousIndentation")
@Composable
fun Header(
    projectScreenKey: Boolean = false,
    text: String,
    actionIcons: List<Int>,
    navController: NavController,
    result: Boolean = false,
    actionTitle: List<String> = emptyList(),
    projectId: Int? = 0
) {
    val projectIdr by remember { mutableStateOf(projectId) }
    val context = LocalContext.current
    var shouldNavigateToAuth by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(31.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ThemeChangingButton(
            darkTheme = AppState.darkTheme,
            onThemeUpdate = { AppState.darkTheme = !AppState.darkTheme },
            navController = navController
        )
        Text(
            text = text,
            modifier = Modifier.weight(1.5f),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(15.dp))) {
            DropdownMenuArea(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }) {
                IconButton(onClick = {
                    expanded = !expanded
                    Log.d("qwerty", actionTitle.toString())
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = "menu_icon",
                        tint = Color.Black
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(Color.White)
                            .border(
                                BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        val itemsToDisplay = if (RoleObject.PMOrAdmin) {
                            // Для администратора
                            actionTitle
                        } else {
                            // Для пользователя только пункты поиска и выхода
                            actionTitle.filterIndexed { index, _ -> index == 0 || index == 4 }
                        }
                        val iconsToDisplay = if (RoleObject.PMOrAdmin) {
                            // Для администратора
                            actionIcons
                        } else {
                            // Для пользователя только пункты поиска и выхода
                            actionIcons.filterIndexed { index, _ -> index == 0 || index == 4 }
                        }

                        itemsToDisplay.forEachIndexed { index, item ->
                            val isLastItem = index == itemsToDisplay.size - 1
                            val iconColor = if(projectScreenKey)if (isLastItem) Color.Red else Color.Black else Color.Black
                            DropdownMenuItem(
                                onClick = {
                                    selectedItem = item
                                    if (projectScreenKey) {
                                        if (item == actionTitle[4]) {
                                            shouldNavigateToAuth = true
                                        } else {
                                            showDialog = true
                                        }
                                    } else {
                                        showDialog = !showDialog
                                    }
                                    expanded = false
                                },
                                text = { Text(text = item) },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = iconsToDisplay[index]),
                                        contentDescription = "action_icon",
                                        tint = iconColor
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = MenuDefaults.itemColors(textColor = iconColor)
                            )
                            if (index < itemsToDisplay.size - 1) {
                                Divider(
                                    color = MaterialTheme.colorScheme.outline,
                                    modifier = Modifier
                                        .height(1.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
            if (showDialog) {
                Dialog(onDismissRequest = { showDialog = false }) {
                    if (projectScreenKey) {
                        if (RoleObject.PMOrAdmin) {
                            when (selectedItem) {
                                actionTitle[0] -> SearchPopUpWindow { showDialog = false }
                                actionTitle[1] -> NewUserWindow(onDismissRequest = {
                                    showDialog = false
                                })

                                actionTitle[2] -> UserList(
                                    checkBoxAble = true,
                                    addRoleButton = false,
                                    buttonText = "Удалить",
                                    showPersonInProject = false,
                                    removeUserWindowKey = true
                                )

                                actionTitle[3] -> NewProjectWindow(onDismissRequest = {
                                    showDialog = false
                                })

                                actionTitle[4] -> shouldNavigateToAuth = !shouldNavigateToAuth
                            }
                        } else {
                            when (selectedItem) {
                                actionTitle[0] -> SearchPopUpWindow { showDialog = false }
                                actionTitle[1] -> shouldNavigateToAuth = !shouldNavigateToAuth
                            }
                        }
                    } else if (!projectScreenKey && RoleObject.PMOrAdmin) {
                        when (selectedItem) {
                            actionTitle[0] -> SearchPopUpWindow { showDialog = false }
                            actionTitle[1] -> UserList(
                                checkBoxAble = false,
                                addRoleButton = true,
                                buttonText = "Добавить пользователя",
                                paddingValue = 20,
                                projectId = projectIdr!!,
                                showPersonInProject = true,
                                removeUserWindowKey = false
                            )
                        }
                    }else{
                        when(selectedItem){
                            actionTitle[0] -> SearchPopUpWindow { showDialog = false }
                        }
                    }
                }
            }
            if (shouldNavigateToAuth) {
                Dialog(onDismissRequest = { shouldNavigateToAuth = !shouldNavigateToAuth }) {
                    ActionNotificationTemplate(
                        onDismissRequest = { shouldNavigateToAuth = !shouldNavigateToAuth },
                        onConfirmation = {
                            shouldNavigateToAuth = !shouldNavigateToAuth
                            AppSettings.setLoginValid(context, false)
                            navController.popBackStack(NavigationItem.Auth.route, inclusive = true)
                            navController.navigate(NavigationItem.Auth.route) {
                                popUpTo(0) { inclusive = true } // This will clear the entire stack
                            }
                        },
                        title = "Выход из аккаунта",
                    )
                }
            }

        }
    }
}

