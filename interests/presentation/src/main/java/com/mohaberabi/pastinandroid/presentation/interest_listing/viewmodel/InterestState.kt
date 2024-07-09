package com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel

import com.mohaberabi.pastinandroid.model.InterestModel


data class InterestState(
    val interests: List<InterestModel> = listOf(),
    val followingIds: Set<String> = setOf()
)

