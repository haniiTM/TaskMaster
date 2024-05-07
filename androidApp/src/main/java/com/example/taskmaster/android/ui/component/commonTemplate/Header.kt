package com.example.taskmaster.android.ui.component.commonTemplate

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.component.popupWindows.NewProjectWindow
import com.example.taskmaster.android.ui.component.popupWindows.NewUserWindow
import com.example.taskmaster.android.ui.component.popupWindows.SearchPopUpWindow
import com.example.taskmaster.android.ui.navigation.NavigationItem
import com.example.taskmaster.android.ui.screens.newUser_screen.NewUserViewModel
import org.koin.androidx.compose.getViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Header(
    projectScreenKey: Boolean = false,
    text: String,
    iconItem: Int,
    actionIcons: List<Int>,
    navController: NavController,
    spacer: Boolean,
    result: Boolean = false,
    viewModel: NewUserViewModel = getViewModel(),
    actionTitle: List<String>
) {
    LaunchedEffect(key1 = true) {
        viewModel.getAllPerson()
    }

    var shouldNavigateToAuth by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }


    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(31.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (spacer) {
            Spacer(modifier = Modifier.weight(0.5f))
        }
        Text(
            text = text,
            modifier = Modifier.weight(1.5f),
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        if (iconItem > 0) {
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = iconItem),
                    contentDescription = "",
                    alignment = Alignment.CenterEnd
                )
                var showDialog by remember { mutableStateOf(false) }
                var selectedItemIndex by remember { mutableIntStateOf(-1) }
                MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(15.dp))) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = !expanded },
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .background(Color.White)
                            .border(
                                BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        actionTitle.filterIndexed { index, _ ->
                            index != 1 && index != 2 && index != 3 || result
                        }.forEach { item ->
                            val isLastItem = item == actionTitle.last()
                            val iconColor = if (projectScreenKey) {
                                if (isLastItem) Color.Red else Color.Black
                            } else {
                                Color.Black
                            }
                            DropdownMenuItem(
                                onClick = {
                                    selectedItemIndex = actionTitle.indexOf(item)
                                    if (actionTitle.indexOf(item) == 4) {
                                        shouldNavigateToAuth = true
                                    } else {
                                        showDialog = true
                                    }
                                    expanded = false
                                },
                                text = { Text(text = item) },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(
                                            id = actionIcons[actionTitle.indexOf(
                                                item
                                            )]
                                        ),
                                        contentDescription = "",
                                        tint = if (projectScreenKey) iconColor else Color.Black
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = MenuDefaults.itemColors(
                                    textColor = if (projectScreenKey) {
                                        if (isLastItem) Color.Red else Color.Black
                                    } else Color.Black
                                )
                            )
                            if (actionTitle.indexOf(item) < actionTitle.size - 1)
                                Divider(
                                    color = MaterialTheme.colorScheme.outline,
                                    modifier = Modifier
                                        .height(1.dp)
                                        .fillMaxWidth()
                                )
                        }
                    }
                }
                if (showDialog) {
                    Dialog(onDismissRequest = { showDialog = false }) {
                        if (projectScreenKey) {
                            when (selectedItemIndex) {
                                0 -> SearchPopUpWindow(onDismissRequest = {
                                    showDialog = !showDialog
                                })

                                1 -> NewUserWindow(
                                    onDismissRequest = {
                                        showDialog = !showDialog
                                    }
                                )

                                2 -> UserList(
                                    checkBoxAble = true,
                                    addRoleButton = false,
                                    buttonText = "Удалить",
                                    showPersonInProject = false,
                                    removeUserWindowKey = true
                                )

                                3 -> NewProjectWindow(
                                    onDismissRequest = {
                                        showDialog = !showDialog
                                    }
                                )
                            }
                        } else {
                            when (selectedItemIndex) {
                                0 -> SearchPopUpWindow(onDismissRequest = {
                                    showDialog = !showDialog
                                })

                                1 -> NewUserWindow(
                                    onDismissRequest = {
                                        showDialog = !showDialog
                                    }
                                )
                            }
                        }
                    }
                }
                if (shouldNavigateToAuth) {
                    LaunchedEffect(Unit) {
                        navController.popBackStack(
                            NavigationItem.Auth.route,
                            inclusive = false,
                            saveState = false
                        )
                    }
                }
            }
        }
    }
}