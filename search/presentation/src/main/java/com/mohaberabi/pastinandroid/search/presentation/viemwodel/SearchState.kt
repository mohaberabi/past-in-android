package com.mohaberabi.pastinandroid.search.presentation.viemwodel

import com.mohaberabi.pastinandroid.model.InterestModel
import com.mohaberabi.pastinandroid.model.NewsModel
import com.mohaberabi.pastinandroid.model.RecentSearchModel
import com.mohaberabi.pastinandroid.model.SearchResultsModel

data class SearchState(
    val foundNews: List<NewsModel> = listOf(),
    val foundInterests: List<InterestModel> = listOf(),
    val isRecentSearchShown: Boolean = true,
)

data class RecentSearchState(
    val recent: List<RecentSearchModel> = listOf()
)

data class UserDataSearchState(
    val followedInterests: Set<String> = setOf(),
    val savedNews: Set<String> = setOf()
)