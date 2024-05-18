package com.example.chatterboticaapp.data.repository

import com.example.chatterboticaapp.data.model.PlayHTRequest
import com.example.chatterboticaapp.data.model.PlayHTResponse
import com.example.chatterboticaapp.data.remote.PlayHTApiService
import com.example.chatterboticaapp.domain.repository.PlayHTRepository

class PlayHTRepositoryImpl(private val apiService: PlayHTApiService): PlayHTRepository {

    override suspend fun postSpeech(text: String): PlayHTResponse {
        val request = PlayHTRequest(text)
        return apiService.postSpeech(request, "", "","", "application/json")
    }

    override suspend fun getSpeech(id: String): PlayHTResponse {
        return apiService.getSpeech(id, "","","application/json", "application/json" )
    }





}