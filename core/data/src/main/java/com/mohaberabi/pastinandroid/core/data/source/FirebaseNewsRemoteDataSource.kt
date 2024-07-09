package com.mohaberabi.pastinandroid.core.data.source

import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.const.EndPoints
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.data.dto.NewsModelDto
import com.mohaberabi.pastinandroid.core.data.mapper.toNewsModel
import com.mohaberabi.pastinandroid.core.common.util.mapFirebaseToAppError

import com.mohaberabi.pastinandroid.core.domain.source.NewsRemoteDataSource

import com.mohaberabi.pastinandroid.model.NewsModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseNewsRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) :
    NewsRemoteDataSource {
    override suspend fun getNews(): AppResult<List<NewsModel>, DataError.Network> {

        return try {
            val newsCollection = firestore.collection(EndPoints.NEWS).get().await()
            val news = newsCollection.documents.map { doc ->
                NewsModelDto.fromMap(doc.data!!).toNewsModel()
            }
            AppResult.Done(news)
        } catch (e: FirebaseException) {
            e.printStackTrace()
            AppResult.Error(e.mapFirebaseToAppError())
        }
    }
}