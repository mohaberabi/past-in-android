package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.pastinandroid.presentation.designsystem.R


data class PiaLayout(
    val rootRoute: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val contentDescription: String? = null,
)


@Composable
fun PiaBottomNav(
    items: List<PiaLayout> = listOf(),
    onItemClick: (String) -> Unit = {},
    selected: (String) -> Boolean,
) {


    BottomAppBar(

        containerColor = Color.Transparent,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                ),
                label = {
                    Text(text = stringResource(id = item.label))
                },
                selected = selected(item.rootRoute),
                onClick = {
                    onItemClick(item.rootRoute)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.contentDescription,
                    )
                },
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPiaBottomNav() {

    PiaTheme {

        val items = listOf(
            PiaLayout("forYou", R.string.for_you, R.drawable.for_you),
            PiaLayout("saved", R.string.for_you, R.drawable.bookmark_outline),
            PiaLayout("interests", R.string.for_you, R.drawable.favorite),
        )
        PiaBottomNav(
            selected = {
                it == "forYou"
            },
            items = items,
        )
    }
}