package com.mohaberabi.pastinandroid.core.domain.repository

import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.InterestModel
import kotlinx.coroutines.flow.Flow

interface InterestRepository {
    fun getAllInterests(): Flow<List<InterestModel>>
    suspend fun fetchInterests(): EmptyDataResult<DataError>

    suspend fun getInterestById(id: String): AppResult<InterestModel, DataError>
}