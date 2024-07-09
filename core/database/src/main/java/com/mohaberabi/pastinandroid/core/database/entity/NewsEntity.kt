package com.mohaberabi.pastinandroid.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant


@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val body: String,
    val url: String,
    val imageUrl: String,
    val related: String,
    val date: String,
    val interestId: String,
)