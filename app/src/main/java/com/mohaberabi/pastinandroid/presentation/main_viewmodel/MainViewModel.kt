package com.mohaberabi.pastinandroid.presentation.main_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pastinandroid.core.domain.NetworkInfo
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkInfo: NetworkInfo
) : ViewModel() {

    val state: StateFlow<PiaMainState> =
        userRepository.getUserData()
            .combine(networkInfo.isOnline) { user, connected ->
                PiaMainState(
                    loaded = true,
                    connectedToNetwork = connected,
                    userData = user
                )
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
                PiaMainState(loaded = false)
            )

}