package com.example.chatterboticaapp.ui.viewmodel

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatterboticaapp.data.model.remote.GeminiAiResponse
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import com.example.chatterboticaapp.domain.repository.PlayHTRepository
import com.example.chatterboticaapp.utils.VoiceToTextParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SpeechListeningViewModel @Inject constructor(
    private val repository: PlayHTRepository,
    private val voiceToTextParser: VoiceToTextParser,
    private val geminiRepository: GeminiAiRepository
): ViewModel() {

    // TTS ------------------
    private var mediaPlayer: MediaPlayer? = null
    private var _isMediaPlaying = MutableStateFlow(false) // State Flow untuk status pemutaran audio
    val isMediaPlayingState: StateFlow<Boolean> = _isMediaPlaying // Properti yang dapat diamati untuk status pemutaran audio

    private val _isPlayHTFetching = MutableStateFlow(false) // State Flow untuk status pemutaran audio
    val isPlayHTFetchingState: StateFlow<Boolean> = _isPlayHTFetching // Properti yang dapat diamati untuk status pemutaran audio

    fun generateSpeech(text: String) {
        _isPlayHTFetching.value = true
        viewModelScope.launch {
            val response = async {
                repository.postSpeech(text)
            }.await()

            Log.d("MyComposable", "Status telah berubah menjadi completeKKK: ${response}")

            while (true) {
                val res= repository.getSpeech(response.id)
//                val res= repository.getSpeech("UaklToKCNRUL8vH3Ic")
                Log.d("MyComposable", "Status telah berubah menjadi completeKKK kedua: ${response}")

                if (res.status == "complete") {
                    Log.d("MyComposable", "Status telah berubah menjadi complete: ${res.status}")
                    Log.d("MyComposable", "Status telah berubah menjadi complete: ${res.output.url}")
                    playAudio(res.output.url)
                    break
                }

                delay(5000)
            }
        }
    }

    private fun playAudio(audioUrl: String) {
        releaseMediaPlayer()

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(audioUrl)
            prepareAsync()
            setOnPreparedListener {
                // Panggil start() setelah persiapan selesai
                // _isPlayHTFetching.value = false
                it.start()
                _isPlayHTFetching.value = false
                _isMediaPlaying.value = true // Set nilai state flow menjadi true ketika audio dimulai
            }
            setOnCompletionListener {
                _isMediaPlaying.value = false // Set nilai state flow menjadi false ketika audio selesai diputar

            }
            setOnErrorListener { mp, what, extra ->
                // Tangani kesalahan jika ada
                false // Mengembalikan false agar kesalahan tidak ditangani oleh MediaPlayer
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
        _isMediaPlaying.value = false // Set nilai state flow menjadi false saat media player dibebaskan
    }


    // SST ---------
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

    // GEMINI AI
    private val _responseRequestPeriodic: MutableList<GeminiAiResponse> = mutableListOf()

    val responseRequestPeriodic: List<GeminiAiResponse>
        get() = _responseRequestPeriodic.toList()

    private fun addResponseRequestPeriodic(param: GeminiAiResponse) {
        _responseRequestPeriodic.add(param)
    }
    // invoked when user exit from chatting screen
    // invoked when user exit from speech listening screen
    fun clearResponseRequestPeriodic() {
        _responseRequestPeriodic.clear()
    }

    fun fetchResponse(query: String, onResponseReceived: (GeminiAiResponse) -> Unit) {
        viewModelScope.launch {
            val result = geminiRepository.getResponse("answer in only 10-20 words : $query")
            addResponseRequestPeriodic(result)
            onResponseReceived(result) // Panggil callback ketika respons diterima
        }
    }


}