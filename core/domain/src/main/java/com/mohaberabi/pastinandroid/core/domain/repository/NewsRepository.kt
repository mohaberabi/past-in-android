package com.mohaberabi.pastinandroid.core.domain.repository

import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {


    suspend fun fetchNews(): EmptyDataResult<DataError>
    fun getRelatedNews(interestId: String): Flow<List<NewsModel>>


    fun getNews(): Flow<List<NewsModel>>
    fun getSavedNews(ids: Set<String>): Flow<List<NewsModel>>
}