package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PiaAppBar(
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    onBackClick: (() -> Unit)? = null,
    title: String,
    navIconContentDescription: String? = null,
    actions: List<(@Composable () -> Unit)> = listOf()
) {

    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehaviour,
        modifier = modifier,
        navigationIcon = {
            onBackClick?.let {

                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = navIconContentDescription
                    )
                }
            }
        },
        actions = {
            actions.forEach {
                it()
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PiaAppBar(
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    title: String,
    navIconContentDescription: String? = null,
    leadingIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {

    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehaviour,
        modifier = modifier,
        navigationIcon = {
            leadingIcon()
        },
        actions = actions,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PiaAppBarPreview() {

    PiaAppBar(
        title = "Search",
        onBackClick = {},
    )
}