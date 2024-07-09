package com.mohaberabi.pastinandroid.core.data.source

import com.mohaberabi.pastinandroid.core.data.mapper.toInterestFtsEntity
import com.mohaberabi.pastinandroid.core.database.dao.InterestsFtsDao
import com.mohaberabi.pastinandroid.core.database.dao.NewsFtsDao
import com.mohaberabi.pastinandroid.core.domain.source.InterestFtsLocalDataSource
import com.mohaberabi.pastinandroid.model.InterestsFts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InterestsFtsRoomLocalDataSource @Inject constructor(
    private val interestsFtsDao: InterestsFtsDao,
) : InterestFtsLocalDataSource {
    override suspend fun insertAll(interests: List<InterestsFts>) =
        interestsFtsDao.insertAll(interests.map { it.toInterestFtsEntity() })

    override fun searchAllInterests(query: String): Flow<List<String>> =
        interestsFtsDao.searchAllInterests(query)
}