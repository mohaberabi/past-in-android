package com.mohaberabi.pastinandroid.core.domain

import kotlinx.coroutines.flow.Flow


interface NetworkInfo {

    val isOnline: Flow<Boolean>

}