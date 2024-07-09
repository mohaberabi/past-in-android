package com.mohaberabi.pastinandroid.core.common.util

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.launchUrlIntent(
    url: String
) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
    startActivity(it)
}