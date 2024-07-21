package com.example.chatterboticaapp.domain.repository

import com.example.chatterboticaapp.data.model.remote.GeminiAiResponse

interface GeminiAiRepository {

    suspend fun getResponse(query: String): GeminiAiResponse
}