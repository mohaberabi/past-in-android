package com.mohaberabi.pastinandroid.core.database.convertors

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class RoomInstantTypeConvertor {


    @TypeConverter
    fun longToInstant(value: Long?): Instant? =
        value?.let(Instant::fromEpochMilliseconds)

    @TypeConverter
    fun instantToLong(instant: Instant?): Long? =
        instant?.toEpochMilliseconds()
}