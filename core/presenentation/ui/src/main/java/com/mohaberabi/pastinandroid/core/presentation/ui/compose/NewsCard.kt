package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.pastinandroid.model.NewsModel
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.BookMarkFilledIcon
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.BookMarkIcon
import kotlinx.datetime.Clock
import java.time.Instant


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    newsModel: NewsModel,
    isSaved: Boolean = false,
    onSaved: () -> Unit = {},
    onClick: () -> Unit = {}

) {
    ElevatedCard(
        modifier = modifier
            .padding(12.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        NetWorkImage(
            url = newsModel.imageUrl
        )
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = newsModel.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(0.8f)
                )



                IconButton(
                    onClick = onSaved,
                    modifier = Modifier.size(40.dp)
                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(
                                40.dp
                            )
                            .background(if (isSaved) MaterialTheme.colorScheme.primary else Color.Transparent)
                    ) {
                        Icon(

                            imageVector = if (isSaved) BookMarkFilledIcon else BookMarkIcon,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }

                }
            }

            Text(
                text = "May 11 ,2024",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    vertical = 4.dp
                )
            )
            Text(
                text = newsModel.title,
                maxLines = 5,
                style = MaterialTheme.typography.bodyMedium,
            )

            FlowRow {

                newsModel.related.forEach { rel ->
                    PiaChip(
                        label = rel,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 80.dp)
                            .padding(horizontal = 4.dp),
                        color = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsCard() {

    PiaTheme {

        NewsCard(
            isSaved = true,
            newsModel = NewsModel(
                "",
                "A new Loser Developer called mohab thinks himself an android developer , hahaha",
                "this loser tought for a while he can be an android developer,this loser tought for a" +
                        " while he can be an android developer,this loser tought for a while he can be an android developer," +
                        "this loser tought for a while he can be an android developer ",
                "",
                "https://loser.com",
                setOf("Android ", "Devs", "Jetpack", "Compose"),
                Clock.System.now()
            )
        )
    }
}