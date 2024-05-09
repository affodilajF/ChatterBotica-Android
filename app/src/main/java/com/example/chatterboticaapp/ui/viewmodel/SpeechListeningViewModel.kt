package com.example.chatterboticaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatterboticaapp.data.model.VoiceToTextParserState
import com.example.chatterboticaapp.utils.VoiceToTextParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SpeechListeningViewModel @Inject constructor(
    private val voiceToTextParser: VoiceToTextParser) : ViewModel(){

    val state = voiceToTextParser.state

    fun stopListening(){
        voiceToTextParser.stopListening()
    }

    fun startListening(existedText : String){
        voiceToTextParser.startListening(existedText = existedText)
    }

    fun clearSpokenText(){
        voiceToTextParser.clearSpokenText()
    }
}


