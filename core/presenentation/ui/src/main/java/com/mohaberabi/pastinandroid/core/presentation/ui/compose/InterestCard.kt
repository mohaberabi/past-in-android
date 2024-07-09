package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.pastinandroid.model.InterestModel
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.NetWorkImage
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.DoneIcon

@Composable
fun InterestCard(
    modifier: Modifier = Modifier,
    interest: InterestModel,
    isFollowing: Boolean,
    onFollow: (InterestModel) -> Unit,
    onClick: (InterestModel) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick(interest) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        com.mohaberabi.pastinandroid.core.presentation.ui.compose.NetWorkImage(
            url = interest.image,
            modifier = Modifier.weight(0.2f),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = interest.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(0.5f),
        )


        IconButton(onClick = { onFollow(interest) }) {


            Box(
                modifier = Modifier
                    .background(
                        if (isFollowing) MaterialTheme.colorScheme.primary
                        else Color.Transparent
                    )
                    .size(40.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    tint = if (isFollowing) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                    imageVector = if (isFollowing) DoneIcon else Icons.Default.Add,
                    contentDescription = "following button"

                )
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewInterestCard() {

    PiaTheme {

        InterestCard(
            onFollow = {},
            onClick = {},
            interest = InterestModel(
                "",
                "",
                "Architecture",
                "Lorem ipsum is the world wide text place holder "
            ), isFollowing = true
        )
    }

}