package com.example.chatterboticaapp.data.repository

import android.app.Application
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.data.remote.OpenAIApi
import com.example.chatterboticaapp.domain.repository.OpenAIApiRepository
import javax.inject.Inject

// we want to pass OpenAiApi interface to this OpenAIApiImpl object
// the way? use the constructor or use danger hilt
//
class OpenAIApiImpl  @Inject constructor(
    private val api : OpenAIApi,
    private val appContext : Application
) : OpenAIApiRepository {

    init{
        val appName = appContext.getString(R.string.app_name)
        print("Hi from OpenAIApiImpl. The app name is $appName")
    }


    override suspend fun doNetworkCall() {
//        TODO("Not yet implemented")
    }


}