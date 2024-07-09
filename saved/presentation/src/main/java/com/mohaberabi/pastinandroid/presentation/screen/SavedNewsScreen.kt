package com.mohaberabi.pastinandroid.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pastinandroid.core.common.util.launchUrlIntent
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.DefaultPiaAppBar
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.NewsLazyList
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaScaffold
import com.mohaberabi.pastinandroid.presentation.designsystem.R
import com.mohaberabi.pastinandroid.presentation.viewmodel.SavedNewsAction
import com.mohaberabi.pastinandroid.presentation.viewmodel.SavedNewsState
import com.mohaberabi.pastinandroid.presentation.viewmodel.SavedViewModel

@Composable
fun SavedNewsScreenRoot(
    savedViewModel: SavedViewModel = hiltViewModel(),


    ) {

    val state = savedViewModel.state.collectAsStateWithLifecycle().value

    val context = LocalContext.current
    SavedNewsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is SavedNewsAction.OnNewsClick -> context.launchUrlIntent(action.url)
                else -> savedViewModel.onAction(action)
            }

        }
    )

}


@Composable
fun SavedNewsScreen(
    state: SavedNewsState,
    onAction: (SavedNewsAction) -> Unit
) {

    NewsLazyList(
        isSaved = { true },
        onSaved = {
            onAction(SavedNewsAction.OnToggleSavedNews(it.id))
        },
        news = state.savedNews,
        onItemClick = {
            onAction(SavedNewsAction.OnNewsClick(it.url))

        }
    )

}