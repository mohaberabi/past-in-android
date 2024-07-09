package com.mohaberabi.pastinandroid.core.data.source

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.domain.source.UserLocalDataSource
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType

import com.mohaberabi.pastinandroid.model.UserDataModel
import com.mohaberabi.pastinandroid.model.toThemeMode
import com.mohaberabi.pastinandroid.model.toThemeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) : UserLocalDataSource {
    override suspend fun saveBookMarks(bookmarks: Set<String>): EmptyDataResult<DataError.Local> {
        return try {
            dataStore.edit { prefs ->
                prefs[BOOKMARKS_KEY] = bookmarks
            }
            AppResult.Done(Unit)
        } catch (e: IOException) {
            e.printStackTrace()
            AppResult.Error(DataError.Local.UNKNOWN)

        }


    }

    override suspend fun saveInterests(interests: Set<String>): EmptyDataResult<DataError.Local> {
        return try {
            dataStore.edit { prefs ->
                prefs[INTERESTS_KEY] = interests
            }
            AppResult.Done(Unit)
        } catch (e: IOException) {
            e.printStackTrace()
            AppResult.Error(DataError.Local.UNKNOWN)

        }


    }

    override suspend fun saveThemeType(
        type:
        ThemeType
    ): EmptyDataResult<DataError.Local> {
        return try {
            dataStore.edit { prefs ->
                prefs[THEME_TYPE_KEY] = type.name
            }
            AppResult.Done(Unit)
        } catch (e: IOException) {
            e.printStackTrace()
            AppResult.Error(DataError.Local.UNKNOWN)

        }
    }

    override suspend fun saveIsDynamicTheme(
        isDynamic:
        Boolean
    ): EmptyDataResult<DataError.Local> {
        return try {
            dataStore.edit { prefs ->
                prefs[DYNAMIC_THEME_KEY] = isDynamic
            }
            AppResult.Done(Unit)
        } catch (e: IOException) {
            e.printStackTrace()
            AppResult.Error(DataError.Local.UNKNOWN)

        }

    }

    override suspend fun saveThemeMode(
        mode:
        ThemeMode
    ): EmptyDataResult<DataError.Local> {
        return try {
            dataStore.edit { prefs ->
                prefs[THEME_MODE_KEY] = mode.name
            }
            AppResult.Done(Unit)
        } catch (e: IOException) {
            e.printStackTrace()
            AppResult.Error(DataError.Local.UNKNOWN)

        }
    }

    override fun getUserData(): Flow<UserDataModel> {
        return dataStore.data.map { prefs ->
            val bookmarks = prefs[BOOKMARKS_KEY] ?: emptySet()
            val intersets = prefs[INTERESTS_KEY] ?: emptySet()
            val themeMode = prefs[THEME_MODE_KEY].toString().toThemeMode()
            val themeType = prefs[THEME_TYPE_KEY].toString().toThemeType()
            val isDynamicTheme = prefs[DYNAMIC_THEME_KEY] ?: false

            UserDataModel(
                bookmarks = bookmarks,
                intersts = intersets,
                useDynamicTheme = isDynamicTheme,
                themePrefs = themeType,
                darkThemePrefs = themeMode
            )
        }
    }

    companion object {
        private val DYNAMIC_THEME_KEY = booleanPreferencesKey("isDynamicTheme")
        private val THEME_MODE_KEY = stringPreferencesKey("themeModeKey")
        private val THEME_TYPE_KEY = stringPreferencesKey("themeTypeKey")
        private val BOOKMARKS_KEY = stringSetPreferencesKey("bookmarks")
        private val INTERESTS_KEY = stringSetPreferencesKey("interests")
    }
}