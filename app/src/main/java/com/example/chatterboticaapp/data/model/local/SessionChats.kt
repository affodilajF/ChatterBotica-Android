package com.example.chatterboticaapp.data.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessionChats")
data class SessionChats(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="timestamp") val timestamp: String,
    @ColumnInfo(name = "chatsJson") val chatsJson: String
)

