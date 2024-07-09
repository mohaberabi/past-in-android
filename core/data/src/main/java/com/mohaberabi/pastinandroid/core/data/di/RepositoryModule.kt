package com.mohaberabi.pastinandroid.core.data.di

import com.mohaberabi.pastinandroid.core.data.repository.DefaultRecentSearchRepository
import com.mohaberabi.pastinandroid.core.data.repository.DefaultSearchRepository
import com.mohaberabi.pastinandroid.core.data.repository.OfflineFirstInterestRepository
import com.mohaberabi.pastinandroid.core.data.repository.OfflineFirstNewsRepository
import com.mohaberabi.pastinandroid.core.domain.repository.InterestRepository
import com.mohaberabi.pastinandroid.core.domain.repository.NewsRepository
import com.mohaberabi.pastinandroid.core.domain.repository.RecentSearchRepository
import com.mohaberabi.pastinandroid.core.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsNewsRepository(
        newsRepository: OfflineFirstNewsRepository,
    ): NewsRepository

    @Binds
    internal abstract fun bindsInterestRepository(
        offlineFirstInterestRepository:
        OfflineFirstInterestRepository
    ): InterestRepository


    @Binds
    internal abstract fun bindsRecentSearchRepository(
        defaultRecentSearchRepository: DefaultRecentSearchRepository
    ): RecentSearchRepository


    @Binds
    internal abstract fun bindsSearchRepository(
        defaultSearchRepository:
        DefaultSearchRepository
    ): SearchRepository
}