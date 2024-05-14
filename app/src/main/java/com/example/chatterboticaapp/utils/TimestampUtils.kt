package com.example.chatterboticaapp.utils

import java.sql.Timestamp

object TimestampUtils {

    fun getCurrentTimestamp(): String {
        return Timestamp(System.currentTimeMillis()).toString()
    }
}