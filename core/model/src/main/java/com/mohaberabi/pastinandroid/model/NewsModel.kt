package com.mohaberabi.pastinandroid.model

import kotlinx.datetime.Instant


data class NewsModel(
    val id: String,
    val title: String,
    val body: String,
    val url: String,
    val imageUrl: String,
    val related: Set<String>,
    val date: Instant
)

