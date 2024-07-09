package com.mohaberabi.pastinandroid.core.data.repository

import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.asEmptyResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.common.util.ApplicationScope
import com.mohaberabi.pastinandroid.core.domain.repository.InterestRepository
import com.mohaberabi.pastinandroid.core.domain.source.InterestLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.InterestRemoteDataSource

import com.mohaberabi.pastinandroid.model.InterestModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstInterestRepository @Inject constructor(
    private val interestLocalDataSource: InterestLocalDataSource,
    private val interestRemoteDataSource: InterestRemoteDataSource,
    @ApplicationScope private val applicationScope: CoroutineScope,
) : InterestRepository {
    override fun getAllInterests(): Flow<List<InterestModel>> =
        interestLocalDataSource.getAllInterests()


    override suspend fun fetchInterests(): EmptyDataResult<DataError> {
        return when (val result = interestRemoteDataSource.getInterests()) {
            is AppResult.Error -> result.asEmptyResult()
            is AppResult.Done -> {
                applicationScope.async {
                    interestLocalDataSource.upsertNews(result.data)
                        .asEmptyResult()
                }.await()
                AppResult.Done(Unit)
            }
        }
    }

    override suspend fun getInterestById(
        id:
        String
    ): AppResult<InterestModel, DataError> =
        interestLocalDataSource.getInterestById(id)
}