package com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel

import com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText


sealed interface InterestDetailEvent {

    data class Error(val error: UiText) : InterestDetailEvent
}