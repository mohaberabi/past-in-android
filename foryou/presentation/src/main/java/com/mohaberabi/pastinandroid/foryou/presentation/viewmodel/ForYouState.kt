package com.mohaberabi.pastinandroid.foryou.presentation.viewmodel

import com.mohaberabi.pastinandroid.model.NewsModel


data class ForYouState(
    val news: List<NewsModel> = listOf(),
    val savedNewsIds: Set<String> = setOf()
)