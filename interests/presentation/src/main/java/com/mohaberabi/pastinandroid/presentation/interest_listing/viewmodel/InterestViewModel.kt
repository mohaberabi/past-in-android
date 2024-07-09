package com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.core.domain.repository.InterestRepository
import com.mohaberabi.pastinandroid.core.presentation.ui.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class InterestViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val interestRepository: InterestRepository,
) : ViewModel() {


    val state: StateFlow<InterestState> =
        userRepository.getUserData()
            .combine(interestRepository.getAllInterests()) { userData, interests ->
                InterestState(
                    interests = interests,
                    followingIds = userData.intersts
                )
            }.stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
                initialValue = InterestState()
            )

    private val _event = Channel<InterestEvent>()
    val event = _event.receiveAsFlow()

    init {

        viewModelScope.launch {
            interestRepository.fetchInterests()
        }

    }


    fun onAction(action: InterestAction) {
        when (action) {

            is InterestAction.OnInterestClick -> {}
            is InterestAction.OnToggleInterestFollow -> onToggleFollowing(action.id)
            else -> {}
        }
    }


    private fun onToggleFollowing(id: String) {
        viewModelScope.launch {
            val followings = state.value.followingIds.toMutableSet()
            if (followings.contains(id)) {
                followings.remove(id)
            } else {
                followings.add(id)
            }
            val result = userRepository.saveInterests(followings)
            if (result is AppResult.Error) {
                _event.send(InterestEvent.Error(result.error.asUiText()))

            }

        }

    }
}