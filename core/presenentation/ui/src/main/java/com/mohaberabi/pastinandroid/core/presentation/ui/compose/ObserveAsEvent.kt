package com.mohaberabi.pastinandroid.core.presentation.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import androidx.lifecycle.repeatOnLifecycle

@Composable
fun <T> ObserveAsEvent(

    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit

) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1, key2, lifecycleOwner.lifecycle, flow) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {


            /**
             * prevent losing of the events sent [immediate]
             */
            withContext(Dispatchers.Main.immediate) {

                flow.collect(onEvent)
            }
        }
    }
}