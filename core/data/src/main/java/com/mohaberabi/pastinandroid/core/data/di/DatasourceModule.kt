package com.mohaberabi.pastinandroid.core.data.di


import com.mohaberabi.pastinandroid.core.data.source.FirebaseInterestRemoteDatasource
import com.mohaberabi.pastinandroid.core.data.source.RoomInterestLocalDatasource
import com.mohaberabi.pastinandroid.core.data.source.FirebaseNewsRemoteDataSource
import com.mohaberabi.pastinandroid.core.data.source.UserDataStore
import com.mohaberabi.pastinandroid.core.data.repository.DefaultUserRepository
import com.mohaberabi.pastinandroid.core.data.source.InterestsFtsRoomLocalDataSource
import com.mohaberabi.pastinandroid.core.data.source.NewsFtsRoomLocalDataSource
import com.mohaberabi.pastinandroid.core.data.source.RecentSearchRoomLocalDataSource
import com.mohaberabi.pastinandroid.core.data.source.RoomNewsLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.core.domain.source.InterestFtsLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.InterestLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.InterestRemoteDataSource
import com.mohaberabi.pastinandroid.core.domain.source.NewsFtsLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.NewsLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.NewsRemoteDataSource
import com.mohaberabi.pastinandroid.core.domain.source.RecentSearchLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.UserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)


abstract class DatasourceModule {

    @Binds
    abstract fun bindsNewsRemoteDataSource(
        remoteDataSource: FirebaseNewsRemoteDataSource,
    ): NewsRemoteDataSource


    @Binds
    abstract fun bindsUserLocalDatasource(
        dataStore: UserDataStore
    ): UserLocalDataSource

    @Binds
    abstract fun bindsUserRepository(
        userRepository: DefaultUserRepository,
    ): UserRepository

    @Binds
    abstract fun bindsNewsLocalDatasource(
        newsLocalDataSource: RoomNewsLocalDataSource,
    ): NewsLocalDataSource


    @Binds
    internal abstract fun bindsInterestsLocalDataSource(
        interestLocalDataSource:
        RoomInterestLocalDatasource
    ): InterestLocalDataSource

    @Binds
    internal abstract fun bindsInterestsRemoteDataSource(
        interestRemoteDataSource: FirebaseInterestRemoteDatasource
    ): InterestRemoteDataSource


    @Binds
    internal abstract fun bindsRecentSearchLocalDataSource(
        roomLocalDataSource: RecentSearchRoomLocalDataSource
    ): RecentSearchLocalDataSource


    @Binds
    internal abstract fun bindsNewsFtsLocalDataSource(
        roomNewsFtsLocalDataSource:
        NewsFtsRoomLocalDataSource
    ): NewsFtsLocalDataSource


    @Binds
    internal abstract fun bindsInterestsFtsLocalDataSource(
        interestsFtsRoomLocalDataSource: InterestsFtsRoomLocalDataSource
    ): InterestFtsLocalDataSource
}