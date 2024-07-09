package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohaberabi.pastinandroid.model.NewsModel


@Composable
fun NewsLazyList(

    modifier: Modifier = Modifier,
    news: List<NewsModel> = listOf(),

    isSaved: (NewsModel) -> Boolean,
    onItemClick: (NewsModel) -> Unit = {},
    onSaved: (NewsModel) -> Unit = {},

    ) {


    LazyColumn(
        modifier = modifier,

        ) {
        items(news) { item ->
            NewsCard(
                onSaved = { onSaved(item) },
                onClick = { onItemClick(item) },
                isSaved = isSaved(item),
                newsModel = item,
            )

        }
    }
}