package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType

import com.mohaberabi.pastinandroid.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveBookMarks(bookmarks: Set<String>): EmptyDataResult<DataError.Local>
    suspend fun saveInterests(interests: Set<String>): EmptyDataResult<DataError.Local>
    suspend fun saveThemeType(type: ThemeType): EmptyDataResult<DataError.Local>
    suspend fun saveIsDynamicTheme(isDynamic: Boolean): EmptyDataResult<DataError.Local>
    suspend fun saveThemeMode(mode: ThemeMode): EmptyDataResult<DataError.Local>

    fun getUserData(): Flow<UserDataModel>
}