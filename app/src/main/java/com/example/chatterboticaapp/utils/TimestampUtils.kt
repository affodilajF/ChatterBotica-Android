package com.example.chatterboticaapp.utils

import android.annotation.SuppressLint
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.abs
import kotlin.math.floor

object TimestampUtils {

    fun getCurrentTimestamp(): String {
        return Timestamp(System.currentTimeMillis()).toString()
    }

    fun getCurrentTimestampLong(): Long {
        return System.currentTimeMillis()
    }


//    @SuppressLint("SimpleDateFormat")
//    fun convertStringToTimestamp(timestampStr: String): String {
//        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        val date = format.parse(timestampStr)
//        val timestamp = date?.time ?: 0L
//
//        val currentTimeMillis = System.currentTimeMillis()
//        val durationMillis = abs(currentTimeMillis - timestamp)
//
//        val hours = floor(durationMillis.toDouble() / (1000 * 60 * 60)).toInt()
//        val minutes = floor((durationMillis.toDouble() / (1000 * 60)) % 60).toInt()
//
//        return "$hours hours $minutes mins ago"
//    }

    fun getDuration(timestamp: Long): String {
        val currentTimeMillis = System.currentTimeMillis()
        val durationMillis = abs(currentTimeMillis - timestamp)

        val hours = floor(durationMillis.toDouble() / (1000 * 60 * 60)).toInt()
        val minutes = floor((durationMillis.toDouble() / (1000 * 60)) % 60).toInt()

        return "$hours hours $minutes mins ago"
    }


    fun getToday() : String{
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE MMMM dd, yyyy 'at' HH:mm a", Locale.US)

        return dateFormat.format(calendar.time)
    }
}