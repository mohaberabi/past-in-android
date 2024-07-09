package com.mohaberabi.pastinandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohaberabi.pastinandroid.common.const.PiaLayouts

import com.mohaberabi.pastinandroid.presentation.main_viewmodel.MainViewModel
import com.mohaberabi.pastinandroid.presentation.pia_compose.PiaComposeAppRoot
import com.mohaberabi.pastinandroid.presentation.pia_compose.rememberPiaAppState

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.state.value.loaded
            }
        }
        setContent {
            val piaAppState = rememberPiaAppState()
            PiaComposeAppRoot(viewModel, piaAppState)

        }
    }
}


