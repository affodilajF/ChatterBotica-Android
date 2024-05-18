package com.example.chatterboticaapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatterboticaapp.data.model.GeminiAiResponse
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerativeAIViewModel @Inject constructor(
    private val repository: GeminiAiRepository
): ViewModel() {

    private val _responseRequestPeriodic: MutableList<GeminiAiResponse> = mutableListOf()

    val responseRequestPeriodic: List<GeminiAiResponse>
        get() = _responseRequestPeriodic.toList()

    private fun addResponseRequestPeriodic(param: GeminiAiResponse) {
        _responseRequestPeriodic.add(param)
    }
    // invoked when user exit from chatting screen
    // invoked when user exit from speech listening screen
    fun clearResponseRequestPeriodic() {
        _responseRequestPeriodic.clear()
    }

    fun fetchResponse(query: String, onResponseReceived: (GeminiAiResponse) -> Unit) {
        viewModelScope.launch {
            val result = repository.getResponse("answer in only 10-20 words : $query")
            addResponseRequestPeriodic(result)
            onResponseReceived(result) // Panggil callback ketika respons diterima
        }
    }
}
