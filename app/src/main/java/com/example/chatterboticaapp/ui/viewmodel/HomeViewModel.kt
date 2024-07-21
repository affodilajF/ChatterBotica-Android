package com.example.chatterboticaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatterboticaapp.data.model.local.SessionChats
import com.example.chatterboticaapp.domain.repository.SessionChatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionChatsRepository: SessionChatsRepository
) : ViewModel(){

    fun getAllHistory(): Flow<List<SessionChats>> {
        return sessionChatsRepository.getSessionChats()
    }

}