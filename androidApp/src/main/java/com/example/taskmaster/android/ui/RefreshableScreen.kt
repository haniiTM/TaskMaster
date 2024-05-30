package com.example.taskmaster.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun RefreshableScreen(
    isRefreshing: Boolean,
    onRefresh: suspend () -> Unit,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val refreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            coroutineScope.launch {
                onRefresh()
            }
        }
    ) {
        content()
    }
}