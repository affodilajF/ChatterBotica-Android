package com.example.chatterboticaapp.data.remote

import retrofit2.http.GET

interface OpenAIApi {

    @GET("test")
    suspend fun doNetworkCall()
}