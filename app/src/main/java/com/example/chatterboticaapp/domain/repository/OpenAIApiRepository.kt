package com.example.chatterboticaapp.domain.repository

interface OpenAIApiRepository {
    suspend fun doNetworkCall()
}