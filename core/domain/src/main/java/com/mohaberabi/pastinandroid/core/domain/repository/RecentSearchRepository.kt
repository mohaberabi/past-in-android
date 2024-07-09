package com.mohaberabi.pastinandroid.core.domain.repository

import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.RecentSearchModel
import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {


    fun getRecentSearchQuery(limit: Int = 12): Flow<List<RecentSearchModel>>
    suspend fun clearAllRecentSearch()
    suspend fun insertOrReplaceRecentSearch(query: String): EmptyDataResult<DataError>
}