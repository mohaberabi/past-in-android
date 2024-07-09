package com.mohaberabi.pastinandroid.core.data.source

import android.database.sqlite.SQLiteFullException
import com.google.android.gms.common.util.Clock
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.data.mapper.toRecentSearchModel
import com.mohaberabi.pastinandroid.core.database.dao.RecentSearchDao
import com.mohaberabi.pastinandroid.core.database.entity.RecentSearchEntity
import com.mohaberabi.pastinandroid.core.domain.source.RecentSearchLocalDataSource
import com.mohaberabi.pastinandroid.model.RecentSearchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.sql.SQLDataException
import javax.inject.Inject

class RecentSearchRoomLocalDataSource @Inject constructor(
    private val recentSearchDao: RecentSearchDao,
) : RecentSearchLocalDataSource {
    override fun getAllRecentSearch(limit: Int): Flow<List<RecentSearchModel>> =
        recentSearchDao.getAllSearchQueries(limit)
            .map { list -> list.map { it.toRecentSearchModel() } }

    override suspend fun insertOrReplaceRecentSearch(query: String): EmptyDataResult<DataError.Local> {

        return try {
            recentSearchDao.insertOrReplaceSearchQuery(
                RecentSearchEntity(
                    query,
                    kotlinx.datetime.Clock.System.now()
                )
            )
            AppResult.Done(Unit)
        } catch (e: SQLiteFullException) {
            AppResult.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun clearAllRecentSearches() = recentSearchDao.clearAllRecentSearches()


}