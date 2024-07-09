package com.mohaberabi.pastinandroid.presentation.main_viewmodel

import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType
import com.mohaberabi.pastinandroid.model.UserDataModel

data class PiaMainState(
    val loaded: Boolean = false,
    val connectedToNetwork: Boolean = true,
    val userData: UserDataModel = UserDataModel()
)
