package com.mohaberabi.pastinandroid.settings.compose

import PiaTheme
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pastinandroid.core.presentation.ui.compose.PiaRadioSelector
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType

import com.mohaberabi.pastinandroid.settings.viewmodel.SettingsAction
import com.mohaberabi.pastinandroid.settings.viewmodel.SettingsState
import com.mohaberabi.pastinandroid.settings.viewmodel.SettingsViewModel


@Composable
fun SettingsDialog(
    viewModel:
    SettingsViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value
    SettingsDialog(
        onAction = viewModel::onAction,
        state = state,
        onDismiss = onDismiss
    )
}

@Composable
fun SettingsDialog(
    modifier: Modifier = Modifier,
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(

        onDismissRequest = {
            onDismiss()
        },
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
                .background(
                    MaterialTheme.colorScheme.surface,
                )
                .fillMaxWidth()
                .padding(16.dp)
        ) {


            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleMedium
            )
            PiaRadioSelector(
                onChanged = {
                    onAction(SettingsAction.OnChangeThemeType(it))
                },
                current = state.themeType,
                groupTitle = "Theme",

                items = ThemeType.entries,
                label = { it.name },
            )

            HorizontalDivider()
            if (state.themeType == ThemeType.DEFAULT) {
                PiaRadioSelector(
                    onChanged = { onAction(SettingsAction.OnToggleDynamicTheme(it)) },
                    current = state.useDynamicTheme,
                    groupTitle = "Use Dynamic theme",
                    items = booleans,
                    label = { it.toHumanReadable() },
                )
                HorizontalDivider()
            }

            PiaRadioSelector(
                onChanged = { onAction(SettingsAction.OnChangeThemeMode(it)) },
                groupTitle = "Dark mode preferences",
                items = ThemeMode.entries,
                current = state.themeMode,
                label = { it.name },
            )
            HorizontalDivider()
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { onDismiss() }) {

                    Text(
                        text = "Ok", style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSettingDialog() {

    PiaTheme(
        darkModePrefs = ThemeMode.LIGHT
    ) {

        SettingsDialog(
            onDismiss = {},
            state = SettingsState(),
            onAction = {}
        )
    }
}

val booleans = listOf(true, false)
fun Boolean.toHumanReadable(): String = if (this) "Yes" else "No"