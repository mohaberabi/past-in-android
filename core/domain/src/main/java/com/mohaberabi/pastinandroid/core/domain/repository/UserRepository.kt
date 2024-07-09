package com.mohaberabi.pastinandroid.core.domain.repository

import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType
import com.mohaberabi.pastinandroid.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    suspend fun saveThemeType(type: ThemeType): EmptyDataResult<DataError>
    suspend fun saveIsDynamicTheme(isDynamic: Boolean): EmptyDataResult<DataError>
    suspend fun saveThemeMode(mode: ThemeMode): EmptyDataResult<DataError>

    suspend fun saveBookMarks(bookmarks: Set<String>): EmptyDataResult<DataError>
    suspend fun saveInterests(interests: Set<String>): EmptyDataResult<DataError>
    fun getUserData(): Flow<UserDataModel>

}