package com.mohaberabi.pastinandroid.core.domain.repository

import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.SearchResultsModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {


    suspend fun saveFtsData(): EmptyDataResult<DataError.Local>

    fun search(query: String): Flow<SearchResultsModel>


}