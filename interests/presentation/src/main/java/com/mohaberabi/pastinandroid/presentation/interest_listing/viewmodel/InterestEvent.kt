package com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel

import com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText

sealed interface InterestEvent {

    data class Error(val error: com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText) :
        InterestEvent


}