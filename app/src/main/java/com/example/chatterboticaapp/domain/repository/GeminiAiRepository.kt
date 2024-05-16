package com.example.chatterboticaapp.domain.repository

import com.example.chatterboticaapp.data.model.GeminiRequestResponse

interface GeminiAiRepository {

    suspend fun getResponse(query: String): GeminiRequestResponse
}