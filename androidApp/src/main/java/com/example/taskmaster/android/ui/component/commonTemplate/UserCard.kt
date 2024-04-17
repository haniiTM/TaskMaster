package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskmaster.android.R

@Composable
fun UserCard(checkBoxAble: Boolean, addRoleButton: Boolean) {
    var checked by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .padding(horizontal = 38.dp)
            .height(45.dp)
            .fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
            Box {
                if (checkBoxAble) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {},
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }
                Icon(painter = painterResource(id = R.drawable.clock_icon), contentDescription = "")
            }
            if (addRoleButton) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.more), contentDescription = "")
                }
            }
        }
    }
}