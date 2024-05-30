package com.example.taskmaster.android

import AppSettings
import AppSettings.getUserRole
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.taskmaster.android.di.presentationModule
import com.example.taskmaster.android.ui.component.StateObject.AppState
import com.example.taskmaster.android.ui.component.StateObject.AuthValidObject
import com.example.taskmaster.android.ui.component.StateObject.RoleObject
import com.example.taskmaster.android.ui.navigation.Navigation
import com.example.taskmaster.android.ui.theme.AppTheme
import com.example.taskmaster.di.initKoin
import com.example.taskmaster.domain.utils.NapierInit
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            )
        )

        super.onCreate(savedInstanceState)
        AppState.darkTheme = AppSettings.getDarkTheme(this) // Устанавливаем состояние darkTheme
        AuthValidObject.AuthValid = AppSettings.getLoginValid(this)
        RoleObject.PMOrAdmin = getUserRole(this)
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
            AppTheme(useDarkTheme = AppState.darkTheme) {
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
    ) { innerPadding ->
        LinearGradientBackground()
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .imePadding()
        ) {
            Column {
                Navigation(
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun LinearGradientBackground() {
    val gradient = Brush.verticalGradient(
        0f to MaterialTheme.colorScheme.secondary,
        0.5f to MaterialTheme.colorScheme.primary
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    )
}
