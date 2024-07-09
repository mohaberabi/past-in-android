package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import PiaTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PiaLoader(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {


        CircularProgressIndicator()
    }
}
@Composable
@Preview(showBackground = true )
private fun  PreviewPiaLoader (){

    PiaTheme {
        PiaLoader()
    }
}