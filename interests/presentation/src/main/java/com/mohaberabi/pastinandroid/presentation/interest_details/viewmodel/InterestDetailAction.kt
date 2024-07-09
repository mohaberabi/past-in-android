package com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel

sealed interface InterestDetailAction {
    data class OnToggleFollowing(val id: String) : InterestDetailAction
    data class OnToggleSaveNews(val id: String) : InterestDetailAction
    data object OnBackClick : InterestDetailAction

    data class OnNewsItemClick(val url: String) : InterestDetailAction
}