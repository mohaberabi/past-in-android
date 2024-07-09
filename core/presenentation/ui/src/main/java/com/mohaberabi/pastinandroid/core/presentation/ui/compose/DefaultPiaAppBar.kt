package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mohaberabi.pastinandroid.presentation.designsystem.R
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.SearchIcon
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.SettingsIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultPiaAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onSettingsClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},

    ) {

    PiaAppBar(

        title = title,
        leadingIcon = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = SearchIcon,
                    contentDescription = stringResource(R.string.settings)
                )
            }
        },
        actions = {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = SettingsIcon,
                    contentDescription = stringResource(R.string.settings)
                )
            }
        },
        navIconContentDescription = ""
    )
}