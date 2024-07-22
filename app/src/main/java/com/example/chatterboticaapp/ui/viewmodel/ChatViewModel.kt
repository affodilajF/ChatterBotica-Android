package com.example.chatterboticaapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatterboticaapp.data.model.local.SessionChats
import com.example.chatterboticaapp.data.model.remote.GeminiAiResponse
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.chatterboticaapp.data.local.converters.JsonConverter
import com.example.chatterboticaapp.domain.repository.SessionChatsRepository
import com.example.chatterboticaapp.utils.PDFUtils
import com.example.chatterboticaapp.utils.TimestampUtils
import com.example.chatterboticaapp.utils.VoiceToTextParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: GeminiAiRepository,
    private val sessionChatsRepository: SessionChatsRepository,
    private val voiceToTextParser: VoiceToTextParser
) : ViewModel() {

    private val jsonConverter = JsonConverter()

    // allchat
    private var _allChat = MutableLiveData<List<GeminiAiResponse>>(emptyList())
    val allChat: LiveData<List<GeminiAiResponse>>
        get() = _allChat

    // fetching state
    private val _isFetching = mutableStateOf(false)
    val isFetching: State<Boolean> get() = _isFetching
    fun setIsFetching(value: Boolean){
        _isFetching.value = value
    }

    // generate pdf
    fun generatePdf(ctx: Context, filename: String){
        val allChatList: List<GeminiAiResponse>? = _allChat.value
        PDFUtils.createPdfFile(allChatList, ctx, filename)
    }

    // placing history session chat
    suspend fun setHistorySessionChat(chatSessionId: Long) {
        sessionChatsRepository.getSessionChatById(chatSessionId)?.let {sessionChats ->
            jsonConverter.fromJson(sessionChats.chatsJson).values.toList().also {
                _allChat.value = it
            }
            sessionChatsRepository.deleteSessionChat(sessionChats)
        }
    }

    // add chat to exact session chat
    suspend fun fetchResponse(query : String) {
        val data = GeminiAiResponse(request = query, response = "")
        addChat(data)
        try {
            val result = withContext(Dispatchers.IO) {
                repository.getResponse(data.request)
            }
            modifyingLastIndexSessionChat(result)
        } catch (e: Exception) {
            Log.e("fetchResponse", "Failed: ${e.message}")
        } finally {
            _isFetching.value = false
        }
    }
    private fun addChat(param: GeminiAiResponse) {
        _allChat.value = _allChat.value.orEmpty().toMutableList().apply {
            this.add(param)
        }
    }
    fun clearChats() {
        _allChat.value = emptyList()
    }
    private fun modifyingLastIndexSessionChat(param: GeminiAiResponse) {
        _allChat.value = _allChat.value.orEmpty().toMutableList().apply {
            if(this.isNotEmpty()) {
                set(lastIndex, param)
            }
        }
    }

    suspend fun insertSessionChatDb() {
        _allChat.value?.takeIf { it.isNotEmpty() }?.let {
            sessionChatsRepository.insertSessionChat(convertChatSession())
        }
    }

    private fun convertChatSession(): SessionChats {
        val chatMap = _allChat.value?.associateBy {UUID.randomUUID().toString() } ?: emptyMap()
        val json = jsonConverter.toJson(chatMap)

        val allChatList: List<GeminiAiResponse>? = _allChat.value
        val lastIndex: Int = allChatList?.lastIndex ?: -1

        var lastGeminiAiResponse = ""
        if (lastIndex != -1) {
            lastGeminiAiResponse = allChatList?.get(lastIndex)?.request.toString()
        }
        return SessionChats(timestamp = TimestampUtils.getCurrentTimestamp(), chatsJson = json, title = lastGeminiAiResponse)
    }


    // speech to text
    val state = voiceToTextParser.state
    var canRecord: MutableState<Boolean> = mutableStateOf(false)


    fun startListening(existedText : String){
        voiceToTextParser.startListening(existedText = existedText)
    }
    fun clearSpokenText(){
        voiceToTextParser.clearSpokenText()
    }

}