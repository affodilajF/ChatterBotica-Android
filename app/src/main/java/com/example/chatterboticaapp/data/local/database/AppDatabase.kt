package com.example.chatterboticaapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chatterboticaapp.data.local.dao.SessionChatsDAO
import com.example.chatterboticaapp.data.model.local.SessionChats

@Database(entities = [SessionChats::class], version = 4)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sessionChatsDao() : SessionChatsDAO

}
