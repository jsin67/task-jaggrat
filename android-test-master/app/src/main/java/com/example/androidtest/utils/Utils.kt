package com.example.androidtest.utils

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*

const val DATA_KEY = "user_info"
const val END_POINT = "stackoverflow"
/**
 * Checks internet connection
 */
fun verifyAvailableNetwork(activity: Context): Boolean {
    val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

/**
 * Converts to string date format
 * @param time: time value
 */
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd.MM.yyyy")
    return format.format(date)
}