package com.mohaberabi.pastinandroid.foryou.presentation.viewmodel

import com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText


sealed class ForYouEvent {

    data class Error(val error: UiText) :
        ForYouEvent()
}