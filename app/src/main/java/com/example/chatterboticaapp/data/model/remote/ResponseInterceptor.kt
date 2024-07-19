package com.example.chatterboticaapp.data.model.remote

import com.google.gson.Gson
import okhttp3.Interceptor

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val response = chain.proceed(chain.request())

        // Dapatkan body string dari response
        val bodyString = response.body?.string()

        // Ubah body string menjadi objek PlayHtResponse
        val playHtResponse = Gson().fromJson(bodyString, PlayHTResponse::class.java)

        // Ambil URL dari link rel="self"
        val selfLink = playHtResponse._links.firstOrNull { it.rel == "self" }?.href


        // Rebuild ulang response karena body sudah dibaca
        return response.newBuilder()
            .body(bodyString?.let { okhttp3.ResponseBody.create(response.body?.contentType(), it) })
            .build()
    }
}