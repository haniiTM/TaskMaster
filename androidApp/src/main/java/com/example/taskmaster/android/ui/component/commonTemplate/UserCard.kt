package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.taskmaster.android.R

@Composable
fun UserCard(checkBoxAble: Boolean, addRoleButton: Boolean, item: String, isSelected: Boolean, onCheckChanged: (Boolean) -> Unit ) {
    var paddingValue = 12
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
            Box(modifier = Modifier.padding(end = 5.dp).weight(.7f)) {
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
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = paddingValue.dp)
                                .border(
                                    BorderStroke(1.dp, Color.Black),
                                    shape = RoundedCornerShape(25.dp)
                                )
                                .clip(shape = RoundedCornerShape(25.dp))
                                .size(25.dp)
                        )
                        Text(
                            text = item,
                            modifier = Modifier
                                .padding(start = 9.dp),
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black
                        )
                    })
            }
            if (addRoleButton) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}