package com.mohaberabi.pastinandroid.search.presentation.screen

import PiaTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pastinandroid.core.common.util.launchUrlIntent
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.InterestCard
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.NewsCard
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.NewsLazyList
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaScaffold
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaTextField
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.SearchIcon
import com.mohaberabi.pastinandroid.search.presentation.viemwodel.RecentSearchState
import com.mohaberabi.pastinandroid.search.presentation.viemwodel.SearchActions
import com.mohaberabi.pastinandroid.search.presentation.viemwodel.SearchState
import com.mohaberabi.pastinandroid.search.presentation.viemwodel.SearchViewModel
import com.mohaberabi.pastinandroid.search.presentation.viemwodel.UserDataSearchState

@Composable
fun SearchScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},

    onGoInterestDetails: (String) -> Unit,
) {

    val context = LocalContext.current
    val searchValue by viewModel.searchQuery.collectAsStateWithLifecycle()

    val searchState by viewModel.searchState.collectAsStateWithLifecycle()

    val recentSearchState by viewModel.recentSearchState.collectAsStateWithLifecycle()


    val userState by viewModel.userState.collectAsStateWithLifecycle()

    SearchScreen(
        onAction = { action ->
            if (action is SearchActions.OnBackClick) {
                onBackClick()
            } else {
                viewModel.onAction(action)
            }

        },
        searchValue = searchValue,
        searchState = searchState,
        recentSearchState = recentSearchState,
        userState = userState,
        onInterestClick = {
            onGoInterestDetails(it)
        },
        onNewsClick = {
            context.launchUrlIntent(it)
        }
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    recentSearchState: RecentSearchState,
    searchState: SearchState,
    searchValue: String,
    userState: UserDataSearchState,
    onAction: (SearchActions) -> Unit,
    onNewsClick: (String) -> Unit,
    onInterestClick: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    PiaScaffold(

        topAppBar = {

            Row(


                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {


                IconButton(onClick = {
                    onAction(SearchActions.OnBackClick)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                }


                PiaTextField(
                    placeHolder = "Search for news or interests",
                    value = searchValue,
                    onDone = {
                        onAction(SearchActions.OnSearchDone(searchValue))
                        keyboardController?.hide()
                    },
                    onChanged = {
                        onAction(SearchActions.OnSearchChanged(it))

                    }
                )

            }


        }
    ) {

            padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {


            if (searchState.foundNews.isEmpty() && searchState.foundInterests.isEmpty()) {
                RecentSearchBuilder(
                    onClick = { onAction(SearchActions.OnRecentSearchClick(it)) },
                    recentSearches = recentSearchState.recent.map { it.query },
                    onClear = {
                        onAction(SearchActions.OnClearRecentSearch)
                    },
                )
            } else {


                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier.padding(
                        8.dp
                    )
                ) {

                    items(
                        items = searchState.foundInterests,
                        key = { it.title },
                    ) {

                            interest ->

                        InterestCard(
                            interest = interest,
                            isFollowing = userState.followedInterests.contains(interest.id),
                            onClick = {
                                onInterestClick(interest.id)
                            },
                            onFollow = {
                                onAction(SearchActions.OnToggleInterest(it.id))
                            },
                        )
                    }

                    items(
                        items = searchState.foundNews, key = { it.id },
                    ) {

                            newsItem ->

                        NewsCard(
                            newsModel = newsItem,
                            onClick = {
                                onNewsClick(newsItem.id)
                            },
                            onSaved = {
                                onAction(SearchActions.OnToggleNews(newsItem.id))
                            },
                            isSaved = userState.savedNews.contains(newsItem.id)
                        )

                    }

                }

            }
        }
    }
}


@Composable
fun RecentSearchBuilder(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onClear: () -> Unit,
    recentSearches: List<String>
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Recent Search",
                style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(0.8f)
            )
            IconButton(
                onClick = {
                    onClear()
                },
            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "clear recent search")
            }
        }
        LazyColumn {
            items(recentSearches) { recent ->
                Text(
                    text = recent,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .clickable { onClick(recent) }
                        .padding(8.dp)
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {


    PiaTheme(
        darkModePrefs = ThemeMode.LIGHT,
    ) {


        SearchScreen(
            searchValue = "Kotlin",
            onAction = {},
            searchState = SearchState(),
            recentSearchState = RecentSearchState(),
            userState = UserDataSearchState(),
            onInterestClick = {},
            onNewsClick = {}
        )
    }
}