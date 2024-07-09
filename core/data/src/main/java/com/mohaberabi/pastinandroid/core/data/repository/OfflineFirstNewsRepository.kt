package com.mohaberabi.pastinandroid.core.data.repository

import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.asEmptyResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.common.util.ApplicationScope
import com.mohaberabi.pastinandroid.core.domain.repository.NewsRepository
import com.mohaberabi.pastinandroid.core.domain.source.NewsLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.NewsRemoteDataSource

import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstNewsRepository @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource,
    @ApplicationScope private val applicationScope: CoroutineScope,
) : NewsRepository {
    override suspend fun fetchNews(): EmptyDataResult<DataError> {

        return when (val result = newsRemoteDataSource.getNews()) {
            is AppResult.Error -> result.asEmptyResult()
            is AppResult.Done -> {
                applicationScope.async {
                    newsLocalDataSource.upsertNews(result.data).asEmptyResult()
                }.await()
                AppResult.Done(Unit)
            }
        }
    }

    override fun getRelatedNews(interestId: String): Flow<List<NewsModel>> =
        newsLocalDataSource.getNewsByInterestId(interestId)

    override fun getNews(): Flow<List<NewsModel>> = newsLocalDataSource.getAllNews()
    override fun getSavedNews(ids: Set<String>): Flow<List<NewsModel>> =
        newsLocalDataSource.getSavedNews(ids)
}