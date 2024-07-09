package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.model.NewsFts
import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsFtsLocalDataSource {


    suspend fun insertAll(news: List<NewsFts>)

    fun searchAllNews(query: String): Flow<List<String>>

}