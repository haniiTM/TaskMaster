package com.example.taskmaster.android.ui.component.popupWindows

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.android.R
import com.example.taskmaster.android.ui.component.commonTemplate.UnifiedTextBox
import com.example.taskmaster.android.ui.screens.newUser_screen.NewUserViewModel
import com.example.taskmaster.android.ui.screens.type_of_activity.TypeOfActivityViewModel
import com.example.taskmaster.android.ui.theme.PlaceHolder
import org.koin.androidx.compose.getViewModel

@Composable
fun NewUserWindow(
    onDismissRequest: () -> Unit,
    viewModel: TypeOfActivityViewModel = getViewModel(),
    viewModelNewUser: NewUserViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getTypeActivity()
    }
    val typeActivity = viewModel.state.value.itemState
    var isValid by remember { mutableStateOf(false) }

    val linearGradient =
        Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onSecondary
            )
        )
    var surname by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var login by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var role = ""

    var expanded by remember {
        mutableStateOf(false)
    }
    var roleValue by remember {
        mutableStateOf("")
    }
    var selectedItemIndex by remember { mutableIntStateOf(-1) }
    val roleIcons = listOf(
        R.drawable.backend_role_icon,
        R.drawable.frontend_role_icon,
        R.drawable.designer_role_icon,
        R.drawable.tester_role_icon,
        R.drawable.project_manager_role_icon,
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 38.dp)
                .clip(shape = RoundedCornerShape(15.dp)).border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(275.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(linearGradient)
                        .fillMaxWidth()
                        .height(40.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Добавить пользователя",
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                UnifiedTextBox(
                    value = surname,
                    onValueChange = { newValue -> surname = newValue },
                    placeholder = "Фамилия",
                    isError = surname.isEmpty()
                )
                UnifiedTextBox(
                    value = name,
                    onValueChange = { newValue -> name = newValue },
                    placeholder = "Имя",
                    isError = name.isEmpty()
                )
                UnifiedTextBox(
                    value = login,
                    onValueChange = { newValue -> login = newValue },
                    placeholder = "Логин",
                    isError = login.isEmpty()
                )
                UnifiedTextBox(
                    value = password,
                    onValueChange = { newValue -> password = newValue },
                    placeholder = "Пароль",
                    isError = password.isEmpty()
                )
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (roleValue == "") {
                                Text(
                                    text = "Выбор роли",
                                    color = PlaceHolder,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.warning),
                                        contentDescription = "",
                                        tint = Color.Red,
                                        modifier = Modifier.size(20.dp).padding(end = 5.dp)
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow_circle_right_icon),
                                        contentDescription = "arrow_circle_right_icon",
                                        modifier = Modifier.rotate(90f),
                                        tint = Color.Black,
                                    )
                                }
                            } else {
                                Text(
                                    text = "Роль:",
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    text = roleValue,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = !expanded },
                                modifier = Modifier
                                    .fillMaxWidth(.61f)
                                    .height(185.dp)
                                    .background(Color.White)
                            ) {
                                typeActivity.forEach { item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedItemIndex = typeActivity.indexOf(item)
                                            roleValue = item?.name ?: ""
                                            role = item?.name ?: ""
                                            expanded = false
                                        },
                                        text = {
                                            Text(
                                                text = item!!.name,
                                                fontWeight = FontWeight.Normal
                                            )
                                        },
                                        trailingIcon = {
                                            Icon(
                                                painter = painterResource(
                                                    id = roleIcons[typeActivity.indexOf(
                                                        item
                                                    )]
                                                ),
                                                contentDescription = "action_icon",
                                                tint = Color.Black
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = MenuDefaults.itemColors(
                                            textColor = Color.Black
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                isValid = if(name.isNotEmpty() && surname.isNotEmpty() && login.isNotEmpty() && password.isNotEmpty() && roleValue.isNotEmpty()) true else false

                Button(
                    onClick = {
                        if(isValid){
                            onDismissRequest()
                            viewModelNewUser.createNewUser(
                                firstName = name,
                                lastName = surname,
                                login = login,
                                password = password,
                                role = roleValue
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, disabledContainerColor = Color.Gray),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    enabled = isValid
                ) {
                    Text(text = "Добавить", color = Color.Black)
                }
            }
        }
    }
}