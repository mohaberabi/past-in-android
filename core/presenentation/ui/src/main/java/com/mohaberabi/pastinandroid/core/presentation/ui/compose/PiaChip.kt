package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun PiaChip(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    color: Color = MaterialTheme.colorScheme.tertiary,
    labelColor: Color = MaterialTheme.colorScheme.primary,
    label: String,
    borderStroke: BorderStroke? = null,
) {
    AssistChip(
        border = borderStroke ?: BorderStroke(0.dp, Color.Transparent),
        modifier = modifier,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = color,
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(color = labelColor)
            )
        },
    )

}

@Preview
@Composable
fun PiaChipPreview() {


    PiaTheme {
        PiaChip(
            label = "Mohab the loser",

            )
    }
}