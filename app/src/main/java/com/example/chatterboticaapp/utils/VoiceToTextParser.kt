package com.example.chatterboticaapp.utils

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.chatterboticaapp.data.model.VoiceToTextParserState
import javax.inject.Inject

class VoiceToTextParser @Inject constructor(private val app:Application) : RecognitionListener{

    private val _state = MutableStateFlow(VoiceToTextParserState())
    val state = _state.asStateFlow()

    val recognizer = SpeechRecognizer.createSpeechRecognizer(app)

    fun clearSpokenText() {
        _state.update {
            it.copy(
                spokenText = ""
            )
        }
    }

    fun startListening(languageCode : String = "en", existedText : String){
        _state.update { VoiceToTextParserState() }

        if(!SpeechRecognizer.isRecognitionAvailable(app)){
            _state.update {
                it.copy(
                    error = "Recognize not available"
                )
            }
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply{
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
        }

        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)
        
        _state.update { currentState ->
            currentState.copy(
                isSpeaking = true,
                spokenText = currentState.spokenText + existedText
            )
        }
    }

    public fun stopListening(){
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
        recognizer.stopListening()
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onReadyForSpeech(params: Bundle?) {
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    override fun onRmsChanged(rmsdB: Float) = Unit

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() {

        _state.update {
            it.copy(
                isSpeaking = false
            )
        }

//        recognizer.stopListening()
    }

    override fun onError(error: Int) {
        if(error== SpeechRecognizer.ERROR_CLIENT){
            return
        }

        _state.update {
            it.copy(
                error = "Error : $error"
            )
        }
    }

    override fun onResults(results: Bundle?) {
        results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { result ->
                _state.update { currentState ->
                    currentState.copy(
                        spokenText = currentState.spokenText + " " + result
                    )
                }
            }
    }

    override fun onPartialResults(partialResults: Bundle?) = Unit


    override fun onEvent(eventType: Int, params: Bundle?) = Unit
}

