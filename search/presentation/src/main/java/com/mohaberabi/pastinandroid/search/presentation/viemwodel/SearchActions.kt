package com.mohaberabi.pastinandroid.search.presentation.viemwodel


sealed interface SearchActions {
    data class OnSearchChanged(val query: String) : SearchActions
    data object OnClearRecentSearch : SearchActions
    data object OnBackClick : SearchActions

    data class OnSearchDone(val query: String) : SearchActions
    data class OnToggleInterest(val interestId: String) : SearchActions
    data class OnToggleNews(val newsId: String) : SearchActions

    data class OnRecentSearchClick(val query: String) : SearchActions
}