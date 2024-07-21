package com.example.chatterboticaapp.data.remote


import com.example.chatterboticaapp.data.model.remote.PlayHTRequest
import com.example.chatterboticaapp.data.model.remote.PlayHTResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PlayHTApiService {


    @POST("tts")
    suspend fun postSpeech(@Body request: PlayHTRequest,
                           @Header("Authorization") authorization: String,
                           @Header("X-USER-ID") userId: String,
                           @Header("accept") acceptHeader: String,
                           @Header("content-type") contentTypeHeader: String): PlayHTResponse
    @GET("tts/{id}")
    suspend fun getSpeech(@Path("id") id: String,
                          @Header("Authorization") authorization: String,
                          @Header("X-USER-ID") userId: String,
                          @Header("accept") acceptHeader: String,
                          @Header("content-type") contentTypeHeader: String): PlayHTResponse

}