package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.InterestModel


interface InterestRemoteDataSource {
    suspend fun getInterests(): AppResult<List<InterestModel>, DataError.Network>

}