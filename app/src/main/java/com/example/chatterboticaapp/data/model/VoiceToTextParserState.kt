package com.example.chatterboticaapp.data.model


data class VoiceToTextParserState(
    var spokenText : String = "",
    val isSpeaking : Boolean = true,
    val error: String? = null
)