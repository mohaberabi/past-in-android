package com.mohaberabi.pastinandroid.presentation.viewmodel

sealed interface SavedNewsAction {

    data object OnSearchClick : SavedNewsAction
    data object OnSettingsClick : SavedNewsAction

    data class OnToggleSavedNews(val id: String) : SavedNewsAction

    data class OnNewsClick(val url: String) : SavedNewsAction
}