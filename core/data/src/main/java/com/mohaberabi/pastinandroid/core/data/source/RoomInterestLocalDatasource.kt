package com.mohaberabi.pastinandroid.core.data.source

import android.database.sqlite.SQLiteFullException
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.database.dao.InterestDao
import com.mohaberabi.pastinandroid.core.domain.source.InterestLocalDataSource

import com.mohaberabi.pastinandroid.core.data.mapper.toInterestEntity
import com.mohaberabi.pastinandroid.core.data.mapper.toInterestModel
import com.mohaberabi.pastinandroid.model.InterestModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomInterestLocalDatasource @Inject constructor(
    private val interestDao: InterestDao,

    ) : InterestLocalDataSource {
    override fun getAllInterests(): Flow<List<InterestModel>> =
        interestDao.getAllInterests().map { items ->
            items.map { it.toInterestModel() }
        }

    override suspend fun upsertNews(
        interests:
        List<InterestModel>
    ): EmptyDataResult<DataError.Local> {
        return try {
            interestDao.upsertAllInterests(interests.map { it.toInterestEntity() })
            AppResult.Done(Unit)
        } catch (e: SQLiteFullException) {
            AppResult.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun getInterestById(
        id:
        String
    ): AppResult<InterestModel, DataError> {
        return when (val entity = interestDao.getInterestById(id)) {
            null -> AppResult.Error(DataError.Local.NOT_FOUND)
            else -> AppResult.Done(entity.toInterestModel())
        }
    }
}