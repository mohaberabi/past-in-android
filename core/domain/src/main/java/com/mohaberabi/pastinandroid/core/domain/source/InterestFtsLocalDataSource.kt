package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.model.InterestModel
import com.mohaberabi.pastinandroid.model.InterestsFts
import kotlinx.coroutines.flow.Flow

interface InterestFtsLocalDataSource {


    suspend fun insertAll(interests: List<InterestsFts>)
    fun searchAllInterests(query: String): Flow<List<String>>
}