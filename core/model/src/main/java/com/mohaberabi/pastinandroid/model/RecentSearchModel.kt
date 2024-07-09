package com.mohaberabi.pastinandroid.model

import kotlinx.datetime.Instant


data class RecentSearchModel(

    val id: String,

    val query: String,

    val searchAt: Instant
)
