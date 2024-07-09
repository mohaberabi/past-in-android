package com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel

import com.mohaberabi.pastinandroid.model.InterestModel


sealed interface InterestAction {


    data class OnInterestClick(val interest: InterestModel) : InterestAction

    data object OnSettingsClick : InterestAction
    data object OnSearchClick : InterestAction

    data class OnToggleInterestFollow(val id: String) : InterestAction
}