package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.pastinandroid.common_kotlin.errors.PiaError

@Composable
fun PiaErrorCard(
    modifier: Modifier = Modifier,
    error: String,
    onRetry: () -> Unit = {}
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {


        Icon(
            imageVector = Icons.Default.Warning,
            tint = Color.Gray,
            contentDescription = error,
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = error,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
        )

        OutlinedButton(
            modifier = Modifier.defaultMinSize(minWidth = 200.dp),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.error),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { onRetry() },
        ) {

            Text(
                text = "Try Again",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPiaErorrCard(modifier: Modifier = Modifier) {
    PiaTheme {
        PiaErrorCard(error = "Oops , Something Went Wrong...")

    }
}