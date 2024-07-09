package com.mohaberabi.pastinandroid.core.domain.source

import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError

import com.mohaberabi.pastinandroid.model.NewsModel

interface NewsRemoteDataSource {


    suspend fun getNews(): AppResult<List<NewsModel>, DataError.Network>
}