package com.mohaberabi.pastinandroid.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {


    val state: StateFlow<SettingsState> =
        userRepository.getUserData().map { user ->
            SettingsState(
                useDynamicTheme = user.useDynamicTheme,
                themeType = user.themePrefs,
                themeMode = user.darkThemePrefs,
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
                initialValue = SettingsState()
            )


    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnChangeThemeMode -> changeThemeMode(action.themeMode)
            is SettingsAction.OnChangeThemeType -> changeThemeType(action.type)
            is SettingsAction.OnToggleDynamicTheme -> changeDynamicTheme()
        }
    }

    private fun changeThemeMode(mode: ThemeMode) =
        viewModelScope.launch { userRepository.saveThemeMode(mode) }


    private fun changeDynamicTheme() =
        viewModelScope.launch { userRepository.saveIsDynamicTheme(!state.value.useDynamicTheme) }

    private fun changeThemeType(type: ThemeType) =
        viewModelScope.launch { userRepository.saveThemeType(type) }

}