package com.example.chatterboticaapp.data.repository

import com.example.chatterboticaapp.data.model.GeminiRequestResponse
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import com.example.chatterboticaapp.utils.TimestampUtils
import com.google.ai.client.generativeai.GenerativeModel

class GeminiAiRepositoryImpl() : GeminiAiRepository {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = " "
    )

    override suspend fun getResponse(query: String): GeminiRequestResponse {
        val response = generativeModel.generateContent(query)

        val geminiRequestResponse = GeminiRequestResponse(
            timestamp = TimestampUtils.getCurrentTimestamp(),
            request = query,
            response = response.text.toString()
        )

        return geminiRequestResponse
    }
}