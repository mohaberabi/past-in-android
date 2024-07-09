package com.mohaberabi.pastinandroid.foryou.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.core.domain.repository.NewsRepository
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.core.presentation.ui.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository,
) : ViewModel() {


    private val _state = MutableStateFlow(ForYouState())

    val state = _state.asStateFlow()

    private val _event = Channel<ForYouEvent>()

    val event = _event.receiveAsFlow()

    init {
        newsRepository.getNews().onEach { newNews ->
            _state.update {
                it.copy(
                    news = newNews
                )
            }

        }.launchIn(viewModelScope)

        userRepository.getUserData().onEach {

                userData ->
            _state.update {
                it.copy(
                    savedNewsIds = userData.bookmarks
                )
            }
        }.launchIn(viewModelScope)

        viewModelScope.launch {
            newsRepository.fetchNews()
        }


    }


    fun onAction(action: ForYouAction) {

        when (action) {
            is ForYouAction.OnToggleSavedNews -> toggleSavedNews(action.id)
            else -> Unit
        }
    }

    private fun toggleSavedNews(id: String) {
        viewModelScope.launch {

            val currentSet = _state.value.savedNewsIds.toMutableSet()


            if (currentSet.contains(id)) {
                currentSet.remove(id)
            } else {
                currentSet.add(id)
            }

            when (val res = userRepository.saveBookMarks(currentSet)) {
                is AppResult.Error -> _event.send(ForYouEvent.Error(res.error.asUiText()))
                else -> Unit
            }
        }
    }
}