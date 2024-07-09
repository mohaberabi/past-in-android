package com.mohaberabi.pastinandroid.core.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.content.getSystemService
import com.mohaberabi.pastinandroid.core.common.util.IoDispatcher
import com.mohaberabi.pastinandroid.core.domain.NetworkInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class AndroidNetworkInfo(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NetworkInfo {

    override val isOnline: Flow<Boolean> = callbackFlow<Boolean> {


        val connectivityManager = context.getSystemService<ConnectivityManager>()

        if (connectivityManager == null) {
            channel.trySend(false)
            channel.close()
            return@callbackFlow
        }
        val callback = object : NetworkCallback() {
            private val networks = mutableSetOf<Network>()
            override fun onAvailable(network: Network) {
                networks += network
                channel.trySend(true)
            }

            override fun onLost(network: Network) {
                networks -= network
                channel.trySend(false)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, callback)

        channel.trySend(connectivityManager.isConnected())
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }

        /**
         * [conflate] get the most recent values only
         */
    }.flowOn(ioDispatcher)
        .conflate()

}

private fun ConnectivityManager.isConnected() = when {
    VERSION.SDK_INT >= VERSION_CODES.M ->
        activeNetwork
            ?.let(::getNetworkCapabilities)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    else -> activeNetworkInfo?.isConnected
} ?: false