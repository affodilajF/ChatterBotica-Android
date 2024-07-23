package com.example.chatterboticaapp.domain.repository

import com.example.chatterboticaapp.data.local.dao.SessionChatsDAO
import com.example.chatterboticaapp.data.model.local.SessionChats
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SessionChatsRepository @Inject constructor(private val sessionChatsDao : SessionChatsDAO) {

    suspend fun deleteSessionChat(data: SessionChats){
        sessionChatsDao.delete(data)
    }
    fun getSessionChats() : Flow<List<SessionChats>>{
        return sessionChatsDao.getSessionChats()
    }
    suspend fun getSessionChatById(id:Long) : SessionChats? {
        return sessionChatsDao.getSessionChatById(id)
    }

    suspend fun insertSessionChat(data: SessionChats){
        sessionChatsDao.insert(data)
    }

    suspend fun updateSessionChat(data: SessionChats){
        sessionChatsDao.update(data)
    }

    suspend fun deleteAll(){
        sessionChatsDao.deleteAll()
    }
}