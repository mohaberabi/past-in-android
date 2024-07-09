package com.mohaberabi.pastinandroid.search.presentation.viemwodel

import android.service.autofill.UserData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.core.domain.repository.RecentSearchRepository
import com.mohaberabi.pastinandroid.core.domain.repository.SearchRepository
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.core.presentation.ui.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRepository: SearchRepository,
    private val recentSearchRepository: RecentSearchRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    companion object {
        const val SEARCH_QUERY_KEY = "SEARCH_QUERY_KEY"
    }

    init {

        viewModelScope.launch {
            searchRepository.saveFtsData()
        }
    }

    val event: Channel<SearchEvent> = Channel()
    val userState: StateFlow<UserDataSearchState> =
        userRepository.getUserData().map { user ->
            UserDataSearchState(
                savedNews = user.bookmarks,
                followedInterests = user.intersts
            )
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            UserDataSearchState()
        )

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY_KEY, initialValue = "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchState = searchQuery.flatMapLatest { query ->
        if (query.trim().isEmpty()) flowOf(SearchState())
        else {
            searchRepository.search(query)
                .map { searchResult ->
                    SearchState(
                        foundInterests = searchResult.interests,
                        foundNews = searchResult.news
                    )
                }
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
        SearchState()
    )


    val recentSearchState: StateFlow<RecentSearchState> =
        recentSearchRepository.getRecentSearchQuery().map { recent ->
            RecentSearchState(recent = recent)
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            RecentSearchState()
        )


    fun onAction(action: SearchActions) {
        when (action) {
            SearchActions.OnBackClick -> Unit
            SearchActions.OnClearRecentSearch -> clearSearch()
            is SearchActions.OnSearchDone -> saveSearchQuery(action.query)
            is SearchActions.OnSearchChanged -> onSearchChanged(action.query)
            is SearchActions.OnRecentSearchClick -> onSearchChanged(action.query)
            is SearchActions.OnToggleInterest -> toggleFollowingOrSaved(action.interestId, true)
            is SearchActions.OnToggleNews -> toggleFollowingOrSaved(action.newsId, false)
        }
    }


    private fun saveSearchQuery(query: String) {
        if (query.isEmpty()) {
            return
        }
        viewModelScope.launch {
            recentSearchRepository.insertOrReplaceRecentSearch(query)
        }
    }

    private fun clearSearch() {
        viewModelScope.launch {
            recentSearchRepository.clearAllRecentSearch()
        }
    }

    private fun onSearchChanged(query: String) {
        savedStateHandle[SEARCH_QUERY_KEY] = query
    }

    private fun toggleFollowingOrSaved(
        id: String,
        isFollowing: Boolean,
    ) {
        val set =
            if (isFollowing) userState.value.followedInterests
            else userState.value.savedNews
        val modified = getSet(set, id)
        viewModelScope.launch {
            val res = if (isFollowing) userRepository.saveInterests(modified)
            else userRepository.saveBookMarks(modified)
            if (res is AppResult.Error) {
                event.send(SearchEvent.Error(res.error.asUiText()))
            }
        }
    }

    private fun getSet(
        set: Set<String>,
        id: String
    ): Set<String> = set.toMutableSet().apply {
        if (contains(id)) remove(id) else add(id)
    }
}