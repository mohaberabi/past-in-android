package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.RecentSearchModel
import kotlinx.coroutines.flow.Flow

interface RecentSearchLocalDataSource {


    fun getAllRecentSearch(limit: Int = 12): Flow<List<RecentSearchModel>>


    suspend fun insertOrReplaceRecentSearch(query: String): EmptyDataResult<DataError.Local>


    suspend fun clearAllRecentSearches()
}