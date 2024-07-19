package com.example.chatterboticaapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatterboticaapp.data.model.remote.GeminiAiResponse
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: GeminiAiRepository
) : ViewModel() {

    // to store one session of chat
    private var _allChat = MutableLiveData<List<GeminiAiResponse>>()

    val allChat: LiveData<List<GeminiAiResponse>>
        get() = _allChat

    private fun addAllChatPeriodic(param: GeminiAiResponse) {
        val currentList = _allChat.value.orEmpty().toMutableList()
        currentList.add(param)
        _allChat.value = currentList
    }
//    fun clearResponseRequestPeriodic() {
//        _allChat.value = emptyList()
//    }

    private fun modifyingLastIndexAllChat(param: GeminiAiResponse){
        val allChatList = _allChat.value
        val currentList = _allChat.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        if (allChatList != null) {
            updatedList[currentList.lastIndex] = param
        }
        _allChat.value = updatedList
    }
    fun fetchResponse(data: GeminiAiResponse) {
        addAllChatPeriodic(data)
        viewModelScope.launch {
            val result = repository.getResponse(data.request)
            Log.d("Aha", result.toString())
            modifyingLastIndexAllChat(result)

        }
    }
}