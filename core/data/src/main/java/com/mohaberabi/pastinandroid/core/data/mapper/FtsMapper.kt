package com.mohaberabi.pastinandroid.core.data.mapper

import com.mohaberabi.pastinandroid.core.database.entity.NewsEntity
import com.mohaberabi.pastinandroid.core.database.entity.NewsFtsEntity
import com.mohaberabi.pastinandroid.model.NewsFts
import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.datetime.Instant

fun NewsEntity.toNewsModel(): NewsModel {
    return NewsModel(
        id = id,
        title = title,
        body = body,
        url = url,
        imageUrl = imageUrl,
        related = related.split(",").toSet(),
        date = Instant.parse(date)
    )
}


fun NewsModel.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        title = title,
        body = body,
        url = url,
        imageUrl = imageUrl,
        related = related.joinToString(","),
        date = date.toString(),
        interestId = if (related.isEmpty()) "" else related.first(),
    )
}

fun NewsModel.toNewsFts(): NewsFts {
    return NewsFts(
        id = id,
        title = title,
        body = body,
    )
}


fun NewsFts.toNewsEntity(): NewsFtsEntity {
    return NewsFtsEntity(
        id = id,
        title = title,
        body = body,
    )
}



