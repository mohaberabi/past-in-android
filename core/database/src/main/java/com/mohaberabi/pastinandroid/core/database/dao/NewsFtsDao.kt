package com.mohaberabi.pastinandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaberabi.pastinandroid.core.database.entity.NewsEntity
import com.mohaberabi.pastinandroid.core.database.entity.NewsFtsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsFtsDao {

    @Query("SELECT id FROM newsFts WHERE newsFts MATCH :query")
    fun searchNews(query: String): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<NewsFtsEntity>)
}


