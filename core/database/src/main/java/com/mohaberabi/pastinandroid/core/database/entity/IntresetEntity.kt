package com.mohaberabi.pastinandroid.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "intresets")
data class InterestEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String,
    val title: String,
    val description: String,
)
