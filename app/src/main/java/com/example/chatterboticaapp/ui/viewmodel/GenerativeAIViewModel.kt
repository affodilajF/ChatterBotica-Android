package com.example.chatterboticaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatterboticaapp.data.model.GeminiRequestResponse
import com.example.chatterboticaapp.utils.TimestampUtils
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenerativeAIViewModel @Inject constructor(): ViewModel() {
//    private val apiKey = BuildConfig.GEMINI_API_KEY

    private val _responseRequestPeriodic: MutableList<GeminiRequestResponse> = mutableListOf()

    val responseRequestPeriodic: List<GeminiRequestResponse>
        get() = _responseRequestPeriodic.toList()


    private fun addResponsesRequestsPeriodic(param: GeminiRequestResponse){
        _responseRequestPeriodic.add(param)
    }

    //    invoked when user exit from chatting screen
//    invoked when user ecit from speech listening screen
    private fun clearResponseRequestPeriodic(){
        _responseRequestPeriodic.clear()
    }

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "AIzaSyBe7LVJujMRD2LHtV2v3nTeBDMS_0b8wqg"
    )


    suspend fun getResponseRequest(query: String) : GeminiRequestResponse {
        val response = generativeModel.generateContent(query)

        val geminiRequestResponse = GeminiRequestResponse(
            timestamp = TimestampUtils.getCurrentTimestamp(),
            request = query,
            response = response.text.toString()
        )

        addResponsesRequestsPeriodic(geminiRequestResponse)

        return geminiRequestResponse
    }

}