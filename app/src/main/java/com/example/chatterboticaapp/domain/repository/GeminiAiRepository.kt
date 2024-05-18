package com.example.chatterboticaapp.domain.repository

import com.example.chatterboticaapp.data.model.GeminiAiResponse

interface GeminiAiRepository {

    suspend fun getResponse(query: String): GeminiAiResponse
}