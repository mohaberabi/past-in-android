package com.mohaberabi.pastinandroid.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.firebase.firestore.FirebaseFirestore
import com.mohaberabi.pastinandroid.core.common.util.IoDispatcher
import com.mohaberabi.pastinandroid.core.common.util.dataStore
import com.mohaberabi.pastinandroid.core.data.repository.AndroidNetworkInfo
import com.mohaberabi.pastinandroid.core.domain.NetworkInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)


@Module
object CoreDataProvidersModule {
    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context:
        Context
    ): DataStore<Preferences> =
        context.dataStore

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()


    @Provides
    @Singleton
    fun provideNetworkInfo(
        @ApplicationContext context: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): NetworkInfo = AndroidNetworkInfo(
        context = context,
        ioDispatcher = ioDispatcher
    )
}