package com.mohaberabi.pastinandroid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.core.domain.repository.NewsRepository
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.core.presentation.ui.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SavedViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val newsRepository: NewsRepository,
) : ViewModel() {


    private val _state = MutableStateFlow(SavedNewsState())

    val state = _state.asStateFlow()

    private val _event = Channel<SavedNewsEvent>()

    val event = _event.receiveAsFlow()

    init {


        userRepository.getUserData().flatMapLatest { userData ->
            _state.update {
                it.copy(
                    savedIds = userData.bookmarks
                )
            }
            newsRepository.getSavedNews(userData.bookmarks).onEach { news ->
                _state.update {
                    it.copy(
                        savedNews = news
                    )
                }
            }

        }.launchIn(viewModelScope)


    }


    fun onAction(action: SavedNewsAction) {
        when (action) {
            is SavedNewsAction.OnToggleSavedNews -> toggleSavedNews(action.id)
            else -> {}
        }
    }

    private fun toggleSavedNews(id: String) {
        viewModelScope.launch {

            val currentSet = _state.value.savedIds.toMutableSet()


            if (currentSet.contains(id)) {
                currentSet.remove(id)
            } else {
                currentSet.add(id)
            }

            when (val res = userRepository.saveBookMarks(currentSet)) {
                is AppResult.Error -> _event.send(SavedNewsEvent.Error(res.error.asUiText()))
                else -> Unit
            }
        }
    }
}