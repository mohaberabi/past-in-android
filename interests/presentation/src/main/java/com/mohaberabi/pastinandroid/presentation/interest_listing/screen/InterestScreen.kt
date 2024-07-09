package com.mohaberabi.pastinandroid.presentation.interest_listing.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.DefaultPiaAppBar
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.InterestCard
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.ObserveAsEvent
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaScaffold
import com.mohaberabi.pastinandroid.presentation.designsystem.R
import com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel.InterestAction
import com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel.InterestEvent
import com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel.InterestState
import com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel.InterestViewModel

@Composable
fun InterestScreenRoot(
    viewModel: InterestViewModel = hiltViewModel(),
    onShowSnackBar: (String) -> Unit = {},
    onGoToDetails: (String) -> Unit,

    ) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    val context = LocalContext.current
    ObserveAsEvent(flow = viewModel.event) {

            event ->
        if (event is InterestEvent.Error) {
            onShowSnackBar(event.error.asString(context))
        }

    }
    InterestScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is InterestAction.OnInterestClick -> onGoToDetails(action.interest.id)
                else -> viewModel.onAction(action)
            }

        }
    )

}

@Composable
fun InterestScreen(
    state: InterestState,
    onAction: (InterestAction) -> Unit
) {


    LazyColumn() {

        items(state.interests) { interestItem ->
            InterestCard(
                onFollow = { interest ->
                    onAction(InterestAction.OnToggleInterestFollow(interest.id))
                },
                onClick = { interest ->
                    onAction(InterestAction.OnInterestClick(interest))

                },
                interest = interestItem, isFollowing =
                state.followingIds.contains(interestItem.id)
            )
        }
    }


}