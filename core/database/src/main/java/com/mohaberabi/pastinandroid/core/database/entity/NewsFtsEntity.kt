package com.mohaberabi.pastinandroid.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey


@Entity(tableName = "newsFts")
@Fts4
data class NewsFtsEntity(
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("body")
    val body: String,
)
