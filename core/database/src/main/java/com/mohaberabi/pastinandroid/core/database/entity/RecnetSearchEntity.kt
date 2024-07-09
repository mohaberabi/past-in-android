package com.mohaberabi.pastinandroid.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant


@Entity(tableName = "searchQueries")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = false)
    val query: String,
    @ColumnInfo
    val searchedAt: Instant
)