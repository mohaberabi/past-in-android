package com.mohaberabi.pastinandroid.common.const

import androidx.compose.runtime.Composable
import com.mohaberabi.pastinandroid.common.navigation.routes.PiaRoutes
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaBottomNav
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaLayout
import com.mohaberabi.pastinandroid.presentation.designsystem.R

val PiaLayouts =
    listOf(
        PiaLayout(
            PiaRoutes.FOR_YOU_LAYOUT,
            R.string.for_you,
            R.drawable.for_you
        ),
        PiaLayout(
            PiaRoutes.SAVED_LAYOUT,
            R.string.saved,
            R.drawable.bookmark_outline
        ),
        PiaLayout(
            PiaRoutes.INTERESTS_LAYOUT,
            R.string.interests,
            R.drawable.favorite
        )
    )

@Composable
fun PrimaryPiaBottomNav(
    onItemClick: (String) -> Unit = {},
    topRoute: String? = PiaRoutes.FOR_YOU_LAYOUT,
    selected: (String) -> Boolean,

    ) {
    PiaBottomNav(

        onItemClick = onItemClick,
        selected = selected,
        items = PiaLayouts,
    )
}