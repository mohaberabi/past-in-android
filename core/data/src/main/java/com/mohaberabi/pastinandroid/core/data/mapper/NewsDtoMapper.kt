package com.mohaberabi.pastinandroid.core.data.mapper

import com.mohaberabi.pastinandroid.core.data.dto.NewsModelDto
import com.mohaberabi.pastinandroid.core.database.entity.NewsEntity
import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


fun NewsModelDto.toNewsModel(): NewsModel {
    return NewsModel(
        id = id,
        title = title,
        body = body,
        url = url,
        imageUrl = imageUrl,
        related = related.toSet(),
        date = Clock.System.now(),
    )
}


fun NewsModelDto.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        title = title,
        body = body,
        url = url,
        imageUrl = imageUrl,
        related = related.joinToString(","),
        interestId = if (related.isEmpty()) "" else related.first(),
        date = date,
    )
}