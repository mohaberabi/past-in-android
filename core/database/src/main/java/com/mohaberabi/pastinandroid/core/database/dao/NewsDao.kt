package com.mohaberabi.pastinandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.pastinandroid.core.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {


    @Query("SELECT * FROM news WHERE interestId=:interestId")
    fun getNewsByInterestId(interestId: String): Flow<List<NewsEntity>>


    @Query("SELECT * FROM news WHERE id IN (:savedIds)")
    fun getSavedNews(savedIds: Set<String>): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news ")
    fun getAllNews(): Flow<List<NewsEntity>>


    @Upsert
    fun upsertNews(news: List<NewsEntity>)
}