package com.mohaberabi.pastinandroid.core.data.repository

import android.database.sqlite.SQLiteFullException
import android.provider.ContactsContract.Data
import com.mohaberabi.pastinandroid.common_kotlin.AppResult
import com.mohaberabi.pastinandroid.common_kotlin.EmptyDataResult
import com.mohaberabi.pastinandroid.common_kotlin.errors.DataError
import com.mohaberabi.pastinandroid.core.common.util.IoDispatcher
import com.mohaberabi.pastinandroid.core.data.mapper.toInterestEntity
import com.mohaberabi.pastinandroid.core.data.mapper.toInterestFts
import com.mohaberabi.pastinandroid.core.data.mapper.toNewsFts
import com.mohaberabi.pastinandroid.core.domain.repository.SearchRepository
import com.mohaberabi.pastinandroid.core.domain.source.InterestFtsLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.InterestLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.NewsFtsLocalDataSource
import com.mohaberabi.pastinandroid.core.domain.source.NewsLocalDataSource
import com.mohaberabi.pastinandroid.model.SearchResultsModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val newsFtsLocalDataSource: NewsFtsLocalDataSource,
    private val interestFtsLocalDataSource: InterestFtsLocalDataSource,
    private val newsLocalDataSource: NewsLocalDataSource,
    private val interestLocalDataSource: InterestLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SearchRepository {


    override suspend fun saveFtsData(): EmptyDataResult<DataError.Local> =
        withContext(ioDispatcher) {
            try {
                val interestsFts =
                    interestLocalDataSource.getAllInterests().first().map { it.toInterestFts() }
                interestFtsLocalDataSource.insertAll(interestsFts)
                val newsFts = newsLocalDataSource.getAllNews().first().map { it.toNewsFts() }
                newsFtsLocalDataSource.insertAll(newsFts)

                AppResult.Done(Unit)
            } catch (e: SQLiteFullException) {
                AppResult.Error(DataError.Local.DISK_FULL)
            }

        }

    /**
     * [mapLatest] populates a result of only the last emitted value by the flow
     */

    /**
     * [distinctUntilChanged] omits duplicated consecutive values of the emitted value
     *fun main() = runBlocking {
     *     val numbersFlow = flowOf(1, 1, 2, 3, 3, 4, 4, 4, 5)
     *
     *     val distinctFlow = numbersFlow.distinctUntilChanged()
     *
     *     distinctFlow.collect { println(it) }
     * }
     * RESULT -> 1,2,3,4,5
     */

    /**
     * [flatMapLatest] transforms the input flow to another output flow of another type
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun search(
        query:
        String
    ): Flow<SearchResultsModel> {

        val newsIds = newsFtsLocalDataSource.searchAllNews("*${query}*")

        val interestsIds = interestFtsLocalDataSource.searchAllInterests("*${query}*")


        val newsFlow =
            newsIds.mapLatest {
                it.toSet()
            }
                .distinctUntilChanged().flatMapLatest { newsLocalDataSource.getAllNews() }

        val interestFlow = interestsIds.mapLatest { it.toSet() }.distinctUntilChanged()
            .flatMapLatest { interestLocalDataSource.getAllInterests() }

        return newsFlow.combine(interestFlow) { news, interests ->
            SearchResultsModel(interests, news)
        }

    }
}