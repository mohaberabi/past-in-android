package com.mohaberabi.pastinandroid.presentation.interest_details.screen

import android.widget.Space
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pastinandroid.core.common.util.launchUrlIntent
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.NetWorkImage
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.NewsLazyList
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaAppBar
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaErrorCard
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaLoader
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaScaffold
import com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel.InterestDetailAction
import com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel.InterestDetailEvent
import com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel.InterestDetailState
import com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel.InterestDetailStatus
import com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel.InterestDetailViewModel


@Composable
fun InterestDetailScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: InterestDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {


    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current
    InterestDetailScreen(
        modifier = modifier,
        onAction = { action ->
            when (action) {

                is InterestDetailAction.OnBackClick -> onBackClick()
                is InterestDetailAction.OnNewsItemClick -> context.launchUrlIntent(
                    action.url
                )

                else -> viewModel.onAction(action)
            }
        },
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestDetailScreen(
    modifier: Modifier = Modifier,
    state: InterestDetailState,
    onAction: (InterestDetailAction) -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState,
    )
    PiaScaffold(
        topAppBar = {

            PiaAppBar(
                scrollBehaviour = scrollBehaviour,
                title = "",
                leadingIcon = {
                    IconButton(onClick = {
                        onAction(InterestDetailAction.OnBackClick)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
    ) {

        Box(modifier = Modifier.padding(it)) {
            when (state.status) {
                InterestDetailStatus.INITIAL -> Unit
                InterestDetailStatus.LOADING -> PiaLoader()
                InterestDetailStatus.ERROR -> PiaErrorCard(error = state.error.asString())
                InterestDetailStatus.POPULATED -> {

                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        NetWorkImage(
                            url = state.interest!!.image,
                            modifier = Modifier.aspectRatio(16 / 9f)
                        )

                        Text(
                            text = state.interest.title,

                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = state.interest.description)

                        NewsLazyList(
                            modifier = Modifier
                                .fillMaxSize()
                                .nestedScroll(scrollBehaviour.nestedScrollConnection),
                            isSaved = { news -> state.savedNewsIds.contains(news.id) },

                            onSaved = { news ->
                                onAction(InterestDetailAction.OnToggleSaveNews(news.id))

                            },
                            onItemClick = {
                                onAction(InterestDetailAction.OnNewsItemClick(it.url))

                            },
                            news = state.relatedNews
                        )
                    }


                }
            }

        }
    }

}