package com.mohaberabi.pastinandroid.core.data.mapper

import com.mohaberabi.pastinandroid.core.database.entity.RecentSearchEntity
import com.mohaberabi.pastinandroid.model.RecentSearchModel


fun RecentSearchModel.toRecentSearchEntity(): RecentSearchEntity {
    return RecentSearchEntity(query, searchAt)
}

fun RecentSearchEntity.toRecentSearchModel(): RecentSearchModel {

    return RecentSearchModel(id = query, query = query, searchAt = searchedAt)

}