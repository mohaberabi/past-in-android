package com.mohaberabi.pastinandroid.model

data class SearchResultsModel(
    val interests: List<InterestModel> = listOf(),
    val news: List<NewsModel> = listOf()
)