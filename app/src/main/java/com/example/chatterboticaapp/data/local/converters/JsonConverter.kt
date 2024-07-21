package com.example.chatterboticaapp.data.local.converters

import androidx.room.TypeConverter
import com.example.chatterboticaapp.data.model.remote.GeminiAiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromJson(json: String): Map<String, GeminiAiResponse> {
        val mapType = object : TypeToken<Map<String, GeminiAiResponse>>() {}.type
        return gson.fromJson(json, mapType)
    }

    @TypeConverter
    fun toJson(map: Map<String, GeminiAiResponse>): String {
        return gson.toJson(map)
    }
}
