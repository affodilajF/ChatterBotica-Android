package com.example.chatterboticaapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chatterboticaapp.utils.VoiceToTextParser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class STTViewModel @Inject constructor(
    private val voiceToTextParser: VoiceToTextParser) : ViewModel(){

    val state = voiceToTextParser.state

    var canRecord: MutableState<Boolean> = mutableStateOf(false)

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


