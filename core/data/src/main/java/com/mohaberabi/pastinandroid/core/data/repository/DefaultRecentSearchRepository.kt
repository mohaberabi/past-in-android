package com.mohaberabi.pastinandroid.core.data.repository

import com.mohaberabi.pastinandroid.core.data.source.RecentSearchRoomLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.repository.RecentSearchRepository
import com.mohaberabi.pastinandroid.core.domain.source.RecentSearchLocalDataSource
import com.mohaberabi.pastinandroid.model.RecentSearchModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRecentSearchRepository @Inject constructor(
    private val recentSearchRoomLocalDataSource: RecentSearchLocalDataSource,
) : RecentSearchRepository {
    override fun getRecentSearchQuery(limit: Int): Flow<List<RecentSearchModel>> =
        recentSearchRoomLocalDataSource.getAllRecentSearch(limit)

    override suspend fun clearAllRecentSearch() =
        recentSearchRoomLocalDataSource.clearAllRecentSearches()

    override suspend fun insertOrReplaceRecentSearch(query: String) =
        recentSearchRoomLocalDataSource.insertOrReplaceRecentSearch(query)
}