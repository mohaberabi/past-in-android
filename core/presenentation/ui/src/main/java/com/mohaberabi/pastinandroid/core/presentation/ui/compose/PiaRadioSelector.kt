package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.pastinandroid.model.ThemeMode


@Composable
fun <T> PiaRadioSelector(
    modifier:
    Modifier = Modifier,
    current: T? = null,
    items: List<T> = listOf(),
    onChanged: (T) -> Unit = {},
    label: (T) -> String,
    groupTitle: String = ""
) {


    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        if (groupTitle.isNotBlank()) {
            Text(
                text = groupTitle,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            )
        }

        repeat(items.size) { index ->
            val item = items[index]
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 65.dp)
                        .weight(0.75f),
                    text = label(item), style = MaterialTheme.typography.bodyMedium,
                )
                RadioButton(

                    selected = item == current,
                    onClick = { onChanged(item) },
                )
            }

        }

    }


}


@Preview(showBackground = false)
@Composable
private fun PreviewPiaRadioSelector() {

    PiaTheme(darkModePrefs = ThemeMode.LIGHT) {
        PiaRadioSelector(
            items = ThemeMode.entries.toList(),
            current = ThemeMode.DARK,
            groupTitle = "Theme Mode",
            label = { it.name }
        )
    }
}