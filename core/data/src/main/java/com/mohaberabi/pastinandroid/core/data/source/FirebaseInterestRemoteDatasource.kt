package com.mohaberabi.pastinandroid.core.data.source

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.const.EndPoints
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.common.util.mapFirebaseToAppError
import com.mohaberabi.pastinandroid.core.domain.source.InterestRemoteDataSource
import com.mohaberabi.pastinandroid.core.data.dto.InterestDto
import com.mohaberabi.pastinandroid.core.data.mapper.toInterestModel
import com.mohaberabi.pastinandroid.model.InterestModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseInterestRemoteDatasource @Inject constructor(

    private val firestore: FirebaseFirestore,
) : InterestRemoteDataSource {
    override suspend fun getInterests(): AppResult<List<InterestModel>, DataError.Network> {

        return try {
            val docs = firestore.collection(EndPoints.INTERESTS)
                .get()
                .await()
            val interests = docs.map { InterestDto.fromMap(it.data).toInterestModel() }
            AppResult.Done(interests)
        } catch (e: FirebaseException) {
            e.printStackTrace()
            AppResult.Error(e.mapFirebaseToAppError())
        }
    }

}