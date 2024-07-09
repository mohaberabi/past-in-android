package com.mohaberabi.pastinandroid.core.database.di

import android.app.Application
import androidx.room.Room
import com.mohaberabi.pastinandroid.core.database.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        app: Application,
    ): NewsDatabase {
        return Room.databaseBuilder(
            app, NewsDatabase::class.java,
            "news.db"
        ).build()
    }


}