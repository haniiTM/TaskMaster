package com.example.taskmaster.android.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.example.taskmaster.android.MyApplicationTheme
import org.koin.androidx.compose.getViewModel


@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
private fun MainScreen(viewModel: MainActivityViewModel = getViewModel()) {
    viewModel.accessToken
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}