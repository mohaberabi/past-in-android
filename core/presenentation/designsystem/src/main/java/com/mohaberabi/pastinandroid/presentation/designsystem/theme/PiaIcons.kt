package com.mohaberabi.pastinandroid.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.mohaberabi.pastinandroid.presentation.designsystem.R

val SearchIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.search)
val ForYouIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.for_you)
val BookMarkFilledIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.bookmark_filled)
val BookMarkIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.bookmark_outline)
val SettingsIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.baseline_settings_24)
val DoneIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.done)