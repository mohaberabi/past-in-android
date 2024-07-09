package com.mohaberabi.pastinandroid.presentation.pia_compose

import PiaTheme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mohaberabi.pastinandroid.common.const.PrimaryPiaBottomNav
import com.mohaberabi.pastinandroid.common.navigation.host.PiaNavGraph
import com.mohaberabi.pastinandroid.common.navigation.host.navigateAndPopUpTo
import com.mohaberabi.pastinandroid.common.navigation.routes.PiaRoutes
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.DefaultPiaAppBar
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaScaffold
import com.mohaberabi.pastinandroid.presentation.main_viewmodel.MainViewModel
import com.mohaberabi.pastinandroid.settings.compose.SettingsDialog
import kotlinx.coroutines.launch

@Composable
fun PiaComposeAppRoot(
    viewModel: MainViewModel,
    piaAppState: PiaAppState,
) {


    val state = viewModel.state.collectAsStateWithLifecycle().value

    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

    PiaTheme(
        theme = state.userData.themePrefs,
        darkModePrefs = state.userData.darkThemePrefs,
        dynamicColor = state.userData.useDynamicTheme
    ) {
        LaunchedEffect(state.connectedToNetwork) {
            if (!state.connectedToNetwork) {
                piaAppState.snackbarHostState.showSnackbar(
                    "You are not connected to internet ",
                    duration = SnackbarDuration.Indefinite
                )
            }
        }

        PiaScaffold(
            topAppBar = {
                if (piaAppState.showAppBar)
                    DefaultPiaAppBar(
                        title = piaAppState.appBarTitle,
                        onSettingsClick = { showSettingsDialog = true },
                        onSearchClick = {
                            piaAppState.navController.navigate(PiaRoutes.SEARCH_LAYOUT)
                        },
                    )
            },
            snackBarHostState = piaAppState.snackbarHostState,
            bottomNavBar = {
                PrimaryPiaBottomNav(
                    selected = {
                        it == piaAppState.currentLaout
                    },
                    onItemClick = { route ->
                        piaAppState.navController.navigateAndPopUpTo(
                            to = route,
                            upTo = piaAppState.navController.graph.findStartDestination().route!!
                        )

                    },
                )
            },
        ) {
            PiaNavGraph(
                modifier = Modifier.padding(it),
                navController = piaAppState.navController,

                onShowSnackBar = {
                    piaAppState.scope.launch {
                        piaAppState.snackbarHostState.showSnackbar(it)
                    }
                }
            )

            if (showSettingsDialog) {
                SettingsDialog(
                    onDismiss =
                    { showSettingsDialog = false }
                )
            }
        }
    }

}

