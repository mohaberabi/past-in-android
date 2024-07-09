package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PiaScaffold(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState? = null,
    topAppBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    bottomNavBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
    ) {


    Scaffold(
        snackbarHost = {
            if (snackBarHostState != null) {
                SnackbarHost(hostState = snackBarHostState)
            }
        },
        bottomBar = bottomNavBar,
        modifier = modifier,
        topBar = topAppBar,
        floatingActionButton = fab,
        floatingActionButtonPosition = FabPosition.Center,
    ) {

            padding ->
        content(padding)


    }
}