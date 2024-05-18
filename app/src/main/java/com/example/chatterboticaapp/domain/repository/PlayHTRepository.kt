package com.example.chatterboticaapp.domain.repository

import com.example.chatterboticaapp.data.model.PlayHTResponse


interface PlayHTRepository{
    suspend fun postSpeech(text: String): PlayHTResponse

    suspend fun getSpeech(id: String): PlayHTResponse
}