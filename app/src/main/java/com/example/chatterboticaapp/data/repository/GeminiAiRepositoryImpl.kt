package com.example.chatterboticaapp.data.repository

import com.example.chatterboticaapp.data.model.remote.GeminiAiResponse
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import com.example.chatterboticaapp.utils.TimestampUtils
import com.google.ai.client.generativeai.GenerativeModel

class GeminiAiRepositoryImpl() : GeminiAiRepository {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",

    )

    override suspend fun getResponse(query: String): GeminiAiResponse {
        val response = generativeModel.generateContent(query)

        val geminiAiResponse = GeminiAiResponse(
            request = query,
            response = response.text.toString()
        )

        return geminiAiResponse
    }
}