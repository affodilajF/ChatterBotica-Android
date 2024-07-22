package com.example.chatterboticaapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.chatterboticaapp.data.model.local.SessionChats
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionChatsDAO {
    @Insert
    suspend fun insert(data: SessionChats)
    @Update
    suspend fun update(data: SessionChats)
    @Query("SELECT * FROM SessionChats ORDER BY timestamp DESC")
    fun getSessionChats(): Flow<List<SessionChats>>
    @Query("SELECT * FROM sessionChats WHERE id=:id")
    suspend fun getSessionChatById(id: Long): SessionChats?
    @Delete
    suspend fun delete(data: SessionChats)
}