package com.example.chatterboticaapp.data.model


data class VoiceToTextParserState(
    var spokenText : String = "",
    val isSpeaking : Boolean = false,
    val error: String? = null
)