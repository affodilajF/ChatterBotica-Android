package com.example.chatterboticaapp.data.repository

import com.example.chatterboticaapp.data.model.remote.PlayHTRequest
import com.example.chatterboticaapp.data.model.remote.PlayHTResponse
import com.example.chatterboticaapp.data.remote.PlayHTApiService
import com.example.chatterboticaapp.domain.repository.PlayHTRepository

class PlayHTRepositoryImpl(private val apiService: PlayHTApiService): PlayHTRepository {
    override suspend fun postSpeech(text: String): PlayHTResponse {
        val request = PlayHTRequest(text)
    }

    override suspend fun getSpeech(id: String): PlayHTResponse {
    }

}