package com.mohaberabi.pastinandroid.foryou.presentation.screen

import PiaTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pastinandroid.core.common.util.launchUrlIntent
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.DefaultPiaAppBar
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.NewsLazyList
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.ObserveAsEvent
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaScaffold
import com.mohaberabi.pastinandroid.foryou.presentation.viewmodel.ForYouAction
import com.mohaberabi.pastinandroid.foryou.presentation.viewmodel.ForYouEvent
import com.mohaberabi.pastinandroid.foryou.presentation.viewmodel.ForYouState
import com.mohaberabi.pastinandroid.foryou.presentation.viewmodel.ForYouViewModel
import com.mohaberabi.pastinandroid.presentation.designsystem.R
import kotlinx.coroutines.Dispatchers


@Composable
fun ForYouScreenRoot(
    viewModel: ForYouViewModel = hiltViewModel(),
    onShowSnackBar: (String) -> Unit = {},

    ) {

    val context = LocalContext.current
    ObserveAsEvent(flow = viewModel.event) { event ->
        when (event) {
            is ForYouEvent.Error -> onShowSnackBar(event.error.asString(context))
        }
    }
    val state = viewModel.state.collectAsStateWithLifecycle().value
    ForYouScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ForYouAction.OnItemClick -> context.launchUrlIntent(action.url)
                else -> viewModel.onAction(action)
            }
        },
    )


}

@Composable
fun ForYouScreen(
    state: ForYouState,
    onAction: (ForYouAction) -> Unit,
) {


    PiaScaffold(

    ) {

        NewsLazyList(
            modifier = Modifier.padding(it),
            onItemClick = { news ->
                onAction(ForYouAction.OnItemClick(news.url))
            },
            news = state.news,
            onSaved = {

                onAction(ForYouAction.OnToggleSavedNews(it.id))

            },
            isSaved = { state.savedNewsIds.contains(it.id) }
        )

    }

}

@Preview(
    showBackground = true
)
@Composable
fun PreviewForYousScreen() {
    PiaTheme {
//        ForYouScreen(
//            onItemClicked = {},
//            ForYouState(),
//            { action ->
//            },
//        )

    }
}