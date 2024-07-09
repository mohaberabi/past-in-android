package com.mohaberabi.pastinandroid.core.data.repository

import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.domain.source.UserLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.repository.UserRepository
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType
import com.mohaberabi.pastinandroid.model.UserDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun saveThemeType(type: ThemeType): EmptyDataResult<DataError> =
        userLocalDataSource.saveThemeType(type)

    override suspend fun saveIsDynamicTheme(isDynamic: Boolean): EmptyDataResult<DataError> =
        userLocalDataSource.saveIsDynamicTheme(isDynamic)

    override suspend fun saveThemeMode(mode: ThemeMode): EmptyDataResult<DataError> =
        userLocalDataSource.saveThemeMode(mode)

    override suspend fun saveBookMarks(bookmarks: Set<String>): EmptyDataResult<DataError> =
        userLocalDataSource.saveBookMarks(bookmarks)

    override suspend fun saveInterests(interests: Set<String>): EmptyDataResult<DataError> =
        userLocalDataSource.saveInterests(interests)

    override fun getUserData(): Flow<UserDataModel> = userLocalDataSource.getUserData()
}