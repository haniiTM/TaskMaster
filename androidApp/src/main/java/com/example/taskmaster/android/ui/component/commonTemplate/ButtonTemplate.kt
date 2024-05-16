package com.example.taskmaster.android.ui.component.commonTemplate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmaster.android.ui.navigation.NavigationItem

@Composable
fun ButtonTemplate(navController: NavController, text: String, width: Int, iconItem: Int = -1, rotateAngle: Float, title: String, id: Int?) {
    if (text == "Вложения"){
        Button(
            onClick = { navController.navigate(NavigationItem.AttachmentsListScreen.passIdAndTitle(id!!.toInt(), title)) }, modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .width(width.dp)
                .height(31.dp)
                .fillMaxSize(), colors = ButtonDefaults.buttonColors(
                Color.White
            ), shape = RoundedCornerShape(10.dp), contentPadding = PaddingValues(15.dp, 0.dp), border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Text(text = text, color = Color.Black)
            if (iconItem > 0) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.rotate(rotateAngle),
                    painter = painterResource(id = iconItem),
                    contentDescription = "arrow_circle_right_icon",
                    alignment = Alignment.CenterEnd
                )
            }
        }
    }else{
        Button(
            onClick = { navController.navigate(NavigationItem.TaskInfo.passIdAndTitle(id!!.toInt(), title)) }, modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .width(width.dp)
                .height(31.dp)
                .fillMaxSize(), colors = ButtonDefaults.buttonColors(
                Color.White
            ), shape = RoundedCornerShape(10.dp), contentPadding = PaddingValues(15.dp, 0.dp), border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Text(text = text, color = Color.Black)
            if (iconItem > 0) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.rotate(rotateAngle),
                    painter = painterResource(id = iconItem),
                    contentDescription = "arrow_circle_right_icon",
                    alignment = Alignment.CenterEnd
                )
            }
        }
    }
}