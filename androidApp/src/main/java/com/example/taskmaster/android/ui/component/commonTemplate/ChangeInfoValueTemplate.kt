package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.taskmaster.android.ui.screens.task_screen.TaskViewModel
import com.example.taskmaster.android.ui.screens.userroleproject_screen.UserroleprojectViewModel
import com.example.taskmaster.data.network.models.TaskDTO
import com.example.taskmaster.data.network.models.UserRoleProjectDTO
import org.koin.androidx.compose.getViewModel

@Composable
fun ChangeInfoValueTemplate(
    title: String,
    value: String,
    placeholder: String,
    onDismissRequest: (Any?) -> Unit,
    timeField: Boolean = false,
    taskId: Int,
    changeTimeEstimation: Boolean,
    triggerRefresh: ((Boolean) -> Unit)? = null,
    viewModelURP: UserroleprojectViewModel = getViewModel(),
    viewTaskModel: TaskViewModel = getViewModel(),
) {
    var changeValue by remember {
        mutableStateOf(value)
    }
    val linearGradient =
        Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onSecondary
            )
        )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(horizontal = 38.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp)
            ) {
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
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                if (timeField){
                    UnifiedTextBox(
                        value = changeValue,
                        onValueChange = { newValue -> changeValue = newValue },
                        placeholder = placeholder,
                        timeUnifiedTextFieldKey = true
                    )
                }
                else{
                    UnifiedTextBox(
                        value = changeValue,
                        onValueChange = { newValue -> changeValue = newValue },
                        placeholder = placeholder
                    )
                }
                Button(
                    onClick =
                    {
                        if(changeTimeEstimation) {
                            val task = TaskDTO()
                            task.scope = changeValue.toInt()
                            viewTaskModel.changeTimeEstimation(
                                taskDTO = task,
                                taskId = taskId
                            ) { success ->
                                if (triggerRefresh != null && success) {
                                    viewTaskModel.dataTaskById(taskId)
                                    triggerRefresh(success)
                                }
                            }
                        } else {
                            val urp = UserRoleProjectDTO(
                                score = changeValue.toInt()
                            )
                            viewModelURP.changeHoursSpent(
                                urp = urp,
                                taskId = taskId
                            ) { success ->
                                if (triggerRefresh != null && success) {
                                    viewTaskModel.dataTaskById(taskId)
                                    triggerRefresh(success)
                                }
                            }
                        }

                        onDismissRequest(changeValue)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(0),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Text(text = "Сохранить", color = Color.Black)
                }
            }
        }
    }
}