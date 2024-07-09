package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.SearchIcon


@Composable
fun PiaTextField(
    modifier:
    Modifier = Modifier,
    value: String = "",
    onChanged: (String) -> Unit = {},
    prefix: @Composable () -> Unit = {},
    keyBoardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    placeHolder: String = "",
    onDone: (KeyboardActionScope.() -> Unit)? = null,
) {
    TextField(

        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType,
            imeAction = imeAction,
        ),
        prefix = prefix,
        keyboardActions = KeyboardActions(onDone = onDone),
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray.copy(alpha = 0.4f))
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Gray.copy(alpha = 0.2f),
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f)
        ),
        shape = RoundedCornerShape(16.dp),
        value = value,
        onValueChange = onChanged,
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewPiaTextField() {


    PiaTheme {
        PiaTextField(
            prefix = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            value = "Kotlin"
        )
    }
}