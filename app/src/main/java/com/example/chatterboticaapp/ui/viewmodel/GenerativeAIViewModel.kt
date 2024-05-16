package com.example.chatterboticaapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatterboticaapp.data.model.GeminiRequestResponse
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import com.example.chatterboticaapp.utils.TimestampUtils
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerativeAIViewModel @Inject constructor(
    private val repository: GeminiAiRepository
): ViewModel() {

    private val _responseRequestPeriodic: MutableList<GeminiRequestResponse> = mutableListOf()
    val responseRequestPeriodic: List<GeminiRequestResponse>
        get() = _responseRequestPeriodic.toList()

    private fun addResponseRequestPeriodic(param: GeminiRequestResponse) {
        _responseRequestPeriodic.add(param)
    }
    // invoked when user exit from chatting screen
    // invoked when user exit from speech listening screen
    private fun clearResponseRequestPeriodic() {
        _responseRequestPeriodic.clear()
    }

    fun fetchResponse(query: String) {
        viewModelScope.launch {
            val result = repository.getResponse(query)
            addResponseRequestPeriodic(result)
            Log.d("MyComposable", "Hasil dari generative model: ${result.response}")
        }
    }



//    private val _responseRequestPeriodic: MutableList<GeminiRequestResponse> = mutableListOf()

//    val responseRequestPeriodic: List<GeminiRequestResponse>
//        get() = _responseRequestPeriodic.toList()


//    private fun addResponsesRequestsPeriodic(param: GeminiRequestResponse){
//        _responseRequestPeriodic.add(param)
//    }

    //    invoked when user exit from chatting screen
//    invoked when user ecit from speech listening screen
//    private fun clearResponseRequestPeriodic(){
//        _responseRequestPeriodic.clear()
//    }

//    private val generativeModel = GenerativeModel(
//        modelName = "gemini-pro",
//        apiKey = "AIzaSyCNLO3UStSUayu00nVC-1dTyORLxl_dU-E"
//    )


//    suspend fun getResponseRequest(query: String) : GeminiRequestResponse {
//        val response = generativeModel.generateContent(query)
//
//        val geminiRequestResponse = GeminiRequestResponse(
//            timestamp = TimestampUtils.getCurrentTimestamp(),
//            request = query,
//            response = response.text.toString()
//        )
//
//        addResponsesRequestsPeriodic(geminiRequestResponse)
//
//        return geminiRequestResponse
//    }

}

//apiKey = "AIzaSyCNLO3UStSUayu00nVC-1dTyORLxl_dU-E"