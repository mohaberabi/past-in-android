package com.mohaberabi.pastinandroid.presentation.viewmodel

import com.mohaberabi.pastinandroid.model.NewsModel


data class SavedNewsState(
    val savedIds: Set<String> = setOf(),
    val savedNews: List<NewsModel> = listOf()
)
