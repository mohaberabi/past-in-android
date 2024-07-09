package com.mohaberabi.pastinandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.pastinandroid.core.database.entity.RecentSearchEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM searchQueries ORDER BY searchedAt DESC LIMIT :limit")
    fun getAllSearchQueries(limit: Int): Flow<List<RecentSearchEntity>>

    @Upsert
    suspend fun insertOrReplaceSearchQuery(query: RecentSearchEntity)

    @Query("DELETE FROM searchQueries")
    suspend fun clearAllRecentSearches()
}