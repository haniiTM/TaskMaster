package com.example.taskmaster.android.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Scaffold
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.taskmaster.android.MyApplicationTheme
import com.example.taskmaster.android.ui.navigation.Navigation
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