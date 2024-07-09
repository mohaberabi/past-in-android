package com.mohaberabi.pastinandroid.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohaberabi.pastinandroid.core.database.convertors.RoomInstantTypeConvertor
import com.mohaberabi.pastinandroid.core.database.dao.InterestDao
import com.mohaberabi.pastinandroid.core.database.dao.InterestsFtsDao
import com.mohaberabi.pastinandroid.core.database.dao.NewsDao
import com.mohaberabi.pastinandroid.core.database.dao.NewsFtsDao
import com.mohaberabi.pastinandroid.core.database.dao.RecentSearchDao
import com.mohaberabi.pastinandroid.core.database.entity.InterestEntity
import com.mohaberabi.pastinandroid.core.database.entity.InterestFtsEntity
import com.mohaberabi.pastinandroid.core.database.entity.NewsEntity
import com.mohaberabi.pastinandroid.core.database.entity.NewsFtsEntity
import com.mohaberabi.pastinandroid.core.database.entity.RecentSearchEntity

@TypeConverters(RoomInstantTypeConvertor::class)
@Database(
    entities = [
        NewsEntity::class,
        InterestEntity::class,
        RecentSearchEntity::class,
        NewsFtsEntity::class,
        InterestFtsEntity::class,

    ],
    version = 1
)


abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
    abstract val interestDao: InterestDao

    abstract val interestFtsDao: InterestsFtsDao
    abstract val newsFtsDao: NewsFtsDao
    abstract val recentSearchDao: RecentSearchDao

}