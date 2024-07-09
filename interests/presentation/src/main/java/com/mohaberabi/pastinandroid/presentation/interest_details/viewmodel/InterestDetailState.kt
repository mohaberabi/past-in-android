package com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel

import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.InterestModel
import com.mohaberabi.pastinandroid.model.NewsModel
import com.mohaberabi.pastinandroid.core.presentation.ui.util.UiText
import com.mohaberabi.pastinandroid.core.presentation.ui.util.asUiText

data class InterestDetailState(
    val choosedInterestId: String = "",
    val interest: InterestModel? = null,
    val status: InterestDetailStatus = InterestDetailStatus.INITIAL,
    val followingIds: Set<String> = emptySet(),
    val savedNewsIds: Set<String> = emptySet(),
    val relatedNews: List<NewsModel> = emptyList(),
    val error: UiText = DataError.Network.UNKNOWN_ERROR.asUiText()
)


enum class InterestDetailStatus {
    INITIAL,
    LOADING,
    ERROR,
    POPULATED
}