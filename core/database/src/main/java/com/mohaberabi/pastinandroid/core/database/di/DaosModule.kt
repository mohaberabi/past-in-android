package com.mohaberabi.pastinandroid.core.database.di

import com.mohaberabi.pastinandroid.core.database.dao.InterestDao
import com.mohaberabi.pastinandroid.core.database.dao.InterestsFtsDao
import com.mohaberabi.pastinandroid.core.database.dao.NewsDao
import com.mohaberabi.pastinandroid.core.database.dao.NewsFtsDao
import com.mohaberabi.pastinandroid.core.database.dao.RecentSearchDao
import com.mohaberabi.pastinandroid.core.database.db.NewsDatabase
import com.mohaberabi.pastinandroid.model.NewsFts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    @Singleton
    fun provideNewsDao(
        database:
        NewsDatabase
    ): NewsDao = database.newsDao


    @Provides
    @Singleton
    fun provideInterestsDao(
        database:
        NewsDatabase
    ): InterestDao = database.interestDao

    @Provides
    @Singleton
    fun provideInterestFtsDao(
        database:
        NewsDatabase
    ): InterestsFtsDao = database.interestFtsDao

    @Provides
    @Singleton
    fun provideNewsFtsDao(
        database:
        NewsDatabase
    ): NewsFtsDao = database.newsFtsDao


    @Provides

    @Singleton
    fun providesRecentSearchDao(
        database:
        NewsDatabase
    ): RecentSearchDao = database.recentSearchDao

}