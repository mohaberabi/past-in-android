package com.mohaberabi.pastinandroid.settings.viewmodel

import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType

sealed interface SettingsAction {
    data class OnChangeThemeMode(val themeMode: ThemeMode) : SettingsAction
    data class OnChangeThemeType(val type: ThemeType) : SettingsAction
    data class OnToggleDynamicTheme(val isDynamic: Boolean) : SettingsAction
}