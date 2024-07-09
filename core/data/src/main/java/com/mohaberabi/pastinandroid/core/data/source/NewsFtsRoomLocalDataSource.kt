package com.mohaberabi.pastinandroid.core.data.source

import com.mohaberabi.pastinandroid.core.data.mapper.toNewsEntity
import com.mohaberabi.pastinandroid.core.database.dao.NewsFtsDao
import com.mohaberabi.pastinandroid.core.domain.source.NewsFtsLocalDataSource
import com.mohaberabi.pastinandroid.model.NewsFts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsFtsRoomLocalDataSource @Inject constructor(
    private val newsFtsDao: NewsFtsDao,
) : NewsFtsLocalDataSource {

    override suspend fun insertAll(news: List<NewsFts>) =
        newsFtsDao.insertAll(news.map { it.toNewsEntity() })

    override fun searchAllNews(query: String): Flow<List<String>> =
        newsFtsDao.searchNews(query)

}