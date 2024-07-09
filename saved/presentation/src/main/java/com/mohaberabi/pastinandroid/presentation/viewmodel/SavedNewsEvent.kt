package com.mohaberabi.pastinandroid.presentation.viewmodel

import com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText

sealed class SavedNewsEvent {


    data class Error(val uiText: com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText) :
        SavedNewsEvent()

}