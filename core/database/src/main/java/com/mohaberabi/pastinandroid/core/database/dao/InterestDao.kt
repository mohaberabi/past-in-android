package com.mohaberabi.pastinandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.pastinandroid.core.database.entity.InterestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestDao {
    @Query("SELECT * FROM intresets")

    fun getAllInterests(): Flow<List<InterestEntity>>

    @Upsert
    suspend fun upsertAllInterests(intrerests: List<InterestEntity>)


    @Query("SELECT * FROM intresets WHERE id=:id")

    suspend fun getInterestById(id: String): InterestEntity?

}