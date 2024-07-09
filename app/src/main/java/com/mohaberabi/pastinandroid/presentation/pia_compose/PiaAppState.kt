package com.mohaberabi.pastinandroid.presentation.pia_compose

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohaberabi.pastinandroid.common.const.PiaLayouts
import kotlinx.coroutines.CoroutineScope

data class PiaAppState(
    val navController: NavHostController,
    val currentLaout: String,
    val scope: CoroutineScope,
    val snackbarHostState: SnackbarHostState,
    val showAppBar: Boolean,
    val appBarTitle: String
)


@Composable
fun rememberPiaAppState(): PiaAppState {
    val piaNavController = rememberNavController()
    val navBackstackEntry by piaNavController.currentBackStackEntryAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val showAppBar =
        PiaLayouts.map { it.rootRoute }.contains(navBackstackEntry?.destination?.route)
    val currentLayout =
        PiaLayouts.firstOrNull { it.rootRoute == navBackstackEntry?.destination?.route }
    val appBarTitle = if (currentLayout == null) "" else stringResource(id = currentLayout.label)
    return remember(
        piaNavController,
        navBackstackEntry,
        scope,
        snackbarHostState,
        showAppBar
    ) {
        PiaAppState(
            navController = piaNavController,
            currentLaout = navBackstackEntry?.destination?.route ?: "",
            scope = scope,
            snackbarHostState = snackbarHostState,
            showAppBar = showAppBar,
            appBarTitle = appBarTitle
        )
    }
}