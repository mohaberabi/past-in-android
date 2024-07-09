package com.mohaberabi.pastinandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Fts4
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaberabi.pastinandroid.core.database.entity.InterestFtsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface InterestsFtsDao {


    @Query("SELECT id FROM interestFts WHERE interestFts MATCH :query")

    fun searchAllInterests(query: String): Flow<List<String>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(interests: List<InterestFtsEntity>)

}