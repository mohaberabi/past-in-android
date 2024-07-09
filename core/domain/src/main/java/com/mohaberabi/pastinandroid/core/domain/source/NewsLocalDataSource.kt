package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    fun getNewsByInterestId(interestId: String): Flow<List<NewsModel>>
    fun getSavedNews(savedIds: Set<String>): Flow<List<NewsModel>>
    fun getAllNews(): Flow<List<NewsModel>>
    suspend fun upsertNews(news: List<NewsModel>): EmptyDataResult<DataError.Local>
}