package com.mohaberabi.pastinandroid.search.presentation.viemwodel

import com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText

sealed interface SearchEvent {
    data class Error(val error: UiText) : SearchEvent
}