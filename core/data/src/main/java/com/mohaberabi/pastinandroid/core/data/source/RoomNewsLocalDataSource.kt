package com.mohaberabi.pastinandroid.core.data.source

import android.database.sqlite.SQLiteFullException
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.data.mapper.toNewsEntity
import com.mohaberabi.pastinandroid.core.data.mapper.toNewsModel
import com.mohaberabi.pastinandroid.core.database.dao.NewsDao
import com.mohaberabi.pastinandroid.core.domain.source.NewsLocalDataSource

import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomNewsLocalDataSource @Inject constructor(
    private val newsDao: NewsDao
) : NewsLocalDataSource {
    override fun getNewsByInterestId(interestId: String): Flow<List<NewsModel>> =
        newsDao.getNewsByInterestId(interestId).map { entitites ->
            entitites.map {
                it.toNewsModel()
            }
        }

    override fun getSavedNews(savedIds: Set<String>): Flow<List<NewsModel>> =
        newsDao.getSavedNews(savedIds).map { entitites ->
            entitites.map {
                it.toNewsModel()
            }
        }

    override fun getAllNews(): Flow<List<NewsModel>> = newsDao.getAllNews().map { entities ->
        entities.map { it.toNewsModel() }
    }

    override suspend fun upsertNews(news: List<NewsModel>): EmptyDataResult<DataError.Local> {
        return try {
            val entities = news.map { it.toNewsEntity() }
            newsDao.upsertNews(entities)
            AppResult.Done(Unit)
        } catch (e: SQLiteFullException) {
            AppResult.Error(DataError.Local.DISK_FULL)
        }
    }
}