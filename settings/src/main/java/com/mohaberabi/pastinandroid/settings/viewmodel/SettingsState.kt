package com.mohaberabi.pastinandroid.settings.viewmodel

import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType

data class SettingsState(
    val themeMode: ThemeMode = ThemeMode.DEFAULT,
    val themeType: ThemeType = ThemeType.DEFAULT,
    val useDynamicTheme: Boolean = false,
)
