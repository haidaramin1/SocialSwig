package com.example.partystarter.utils

import android.app.Activity
import android.content.pm.ActivityInfo

fun lockOrientation(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

fun unlockOrientation(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}
