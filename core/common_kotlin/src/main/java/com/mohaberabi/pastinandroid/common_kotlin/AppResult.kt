package com.mohaberabi.pastinandroid.common_kotlin

import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.common_kotlin.errors.PiaError
import com.mohaberabi.pastinandroid.common_kotlin.errors.PiaException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


sealed interface AppResult<out D, out E : PiaError> {
    data class Done<out D>(val data: D) : AppResult<D, Nothing>


    data class Error<out E : PiaError>(val error: E) :
        AppResult<Nothing, E>

}


/**
 * [inline] eliminates creating  a new function call stack each  time the method was called
 */


inline fun <T, E : PiaError, R> AppResult<T, E>.map(
    map: (T) -> R
): AppResult<R, E> {

    return when (this) {
        is AppResult.Done -> AppResult.Done(map(data))
        is AppResult.Error -> AppResult.Error(error)
    }
}
typealias EmptyDataResult<E> = AppResult<Unit, E>

fun <T, E : PiaError> AppResult<T, E>.asEmptyResult(): EmptyDataResult<E> {
    return map { Unit }
}

fun <T> Flow<T>.asResult(): Flow<AppResult<T, PiaError>> {
    return map<T, AppResult<T, PiaError>> { AppResult.Done(it) }
        .catch { emit(AppResult.Error(PiaException(it))) }
}