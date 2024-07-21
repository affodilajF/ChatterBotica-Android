package com.example.chatterboticaapp.data.model.local


data class VoiceToTextParserState(
    var spokenText : String = "",
    val isSpeaking : Boolean = false,
    val error: String? = null
)