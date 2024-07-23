package com.example.chatterboticaapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatterboticaapp.data.model.local.SessionChats
import com.example.chatterboticaapp.domain.repository.SessionChatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionChatsRepository: SessionChatsRepository
) : ViewModel() {


    fun deleteAll() {
        viewModelScope.launch {
            sessionChatsRepository.deleteAll()
            getAllHistory()
        }
    }

    private var _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private var _isHistoryEmpty = MutableStateFlow<Boolean>(false)
    val isHistoryEmpty: StateFlow<Boolean> get() = _isHistoryEmpty

    private val _historyChat = MutableStateFlow<List<SessionChats>>(emptyList())
    val historyChat: StateFlow<List<SessionChats>> get() = _historyChat

    //
    suspend fun getAllHistory() {
        sessionChatsRepository.getSessionChats()
            .onEach { chats ->
                _isHistoryEmpty.value = chats.isEmpty()
                _historyChat.value = chats
                delay(1000)
                _isLoading.value = false
            }
            .collect()
    }
}


