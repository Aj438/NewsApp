package com.android.newsapp.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

fun Float.round(): Float = (this * 10.0f).roundToInt() / 10.0f

fun currentDate(): String {
    val formatter = SimpleDateFormat("EEEE, dd MMM y", Locale.getDefault())
    return formatter.format(Calendar.getInstance().time)
}