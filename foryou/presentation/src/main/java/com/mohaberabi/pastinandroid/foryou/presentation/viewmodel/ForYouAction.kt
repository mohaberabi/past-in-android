package com.mohaberabi.pastinandroid.foryou.presentation.viewmodel

sealed interface ForYouAction {


    data class OnToggleSavedNews(val id: String) : ForYouAction
    data object OnSettingsClick : ForYouAction
    data object OnSearchClick : ForYouAction
    data class OnItemClick(val url: String) : ForYouAction
}