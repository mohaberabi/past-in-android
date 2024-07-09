package com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.asResult
import com.mohaberabi.pastinandroid.core.domain.repository.InterestRepository
import com.mohaberabi.pastinandroid.core.domain.repository.NewsRepository
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText
import com.mohaberabi.pastinandroid.core.presentation.ui.util.asUiText
import com.mohaberabi.pastinandroid.presentation.interest_listing.viewmodel.InterestAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

object InterestNavConst {
    const val INTEREST_ID_ARG = "interestId"
    const val DETAIL_ROUTE = "detailRoute"
}

@HiltViewModel
class InterestDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
    private val interestRepository: InterestRepository,
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val interestId: String? = savedStateHandle[InterestNavConst.INTEREST_ID_ARG]


    private val _event = Channel<InterestDetailEvent>()
    val event = _event.receiveAsFlow()
    val state: StateFlow<InterestDetailState> = constructState(interestId!!)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            InterestDetailState(status = InterestDetailStatus.LOADING)
        )


    fun onAction(action: InterestDetailAction) {

        when (action) {
            is InterestDetailAction.OnToggleSaveNews -> toggleFollowingOrSaved(action.id, false)
            is InterestDetailAction.OnToggleFollowing -> toggleFollowingOrSaved(action.id, true)
            else -> Unit
        }
    }

    private fun constructState(interestId: String): Flow<InterestDetailState> {
        val interestFlow = flow {
            when (val res = interestRepository.getInterestById(interestId)) {
                is AppResult.Done -> emit(res.data)
                is AppResult.Error -> {
                    InterestDetailState(
                        status = InterestDetailStatus.ERROR,
                        error = UiText.DynamicString(
                            "Error getting interest ${res.error}"
                        )
                    )
                    return@flow
                }

            }

        }
        val userFlow = userRepository.getUserData()
        val newsFlow = newsRepository.getRelatedNews(interestId)

        return combine(
            userFlow,
            newsFlow,
            interestFlow,
            ::Triple
        ).asResult().map { tripleResult ->
            when (tripleResult) {
                is AppResult.Error -> InterestDetailState(
                    status = InterestDetailStatus.ERROR,
                    error = UiText.DynamicString(
                        "Error getting interest ${tripleResult.error}",
                    )
                )

                is AppResult.Done -> {
                    val (user, news, interest) = tripleResult.data
                    InterestDetailState(
                        followingIds = user.intersts,
                        relatedNews = news,
                        interest = interest,
                        savedNewsIds = user.bookmarks,
                        choosedInterestId = interestId,
                        status = InterestDetailStatus.POPULATED
                    )
                }
            }

        }
    }


    private fun toggleFollowingOrSaved(
        id: String,
        isFollowing: Boolean,
    ) {
        val set =
            if (isFollowing) state.value.followingIds
            else state.value.savedNewsIds
        val modified = getSet(set, id)
        viewModelScope.launch {
            val res = if (isFollowing) userRepository.saveInterests(modified)
            else userRepository.saveBookMarks(modified)
            if (res is AppResult.Error) {
                _event.send(InterestDetailEvent.Error(res.error.asUiText()))
            }
        }
    }

    private fun getSet(
        set: Set<String>,
        id: String
    ): Set<String> = set.toMutableSet().apply {
        if (contains(id)) remove(id) else add(id)
    }
}