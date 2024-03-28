package com.example.taskmaster.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.taskmaster.android.di.presentationModule
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.example.taskmaster.android.ui.navigation.Navigation
import com.example.taskmaster.di.initKoin
import com.example.taskmaster.domain.utils.NapierInit
import com.taskmaster.ui.theme.TaskMasterTheme
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val appModules = listOf(presentationModule)

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainActivity)
            modules(appModules)
        }

        if (BuildConfig.DEBUG) {
            NapierInit().init()
        }

        setContent {
            TaskMasterTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
private fun MainScreen() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        bottomBar = {}
    ) {
        Navigation(
            navController = navController,
        )
    }
}