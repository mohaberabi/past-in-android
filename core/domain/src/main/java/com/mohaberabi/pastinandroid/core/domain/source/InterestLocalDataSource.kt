package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.InterestModel
import kotlinx.coroutines.flow.Flow
import javax.xml.crypto.Data

interface InterestLocalDataSource {
    fun getAllInterests(): Flow<List<InterestModel>>
    suspend fun upsertNews(interests: List<InterestModel>): EmptyDataResult<DataError.Local>


    suspend fun getInterestById(id: String): AppResult<InterestModel, DataError>
}