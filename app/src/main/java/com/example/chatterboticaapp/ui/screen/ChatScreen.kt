package com.example.chatterboticaapp.ui.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver
import android.view.WindowInsetsController
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chatterboticaapp.data.model.GeminiAiResponse
import com.example.chatterboticaapp.ui.component.ChatMenuUtility
import com.example.chatterboticaapp.ui.component.TextMessageRequest
import com.example.chatterboticaapp.ui.component.TextMessageResponse
import com.example.chatterboticaapp.ui.viewmodel.ChatViewModel


@Composable
fun ChatScreen() {
    var text by remember { mutableStateOf("") }
    val chatViewModel: ChatViewModel = hiltViewModel()
    val itemListState by chatViewModel.allChat.observeAsState(initial = emptyList())

    val lazyListState = rememberLazyListState()
    val isKeyboardVisible by keyboardAsState()

    LaunchedEffect(itemListState, isKeyboardVisible) {
        if (itemListState.isNotEmpty() && isKeyboardVisible) {
            lazyListState.scrollToItem(itemListState.size-1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(top = 18.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(itemListState) { item ->
                    TextMessageRequest(username = "Request", text = item.request)
                    var isResponseReady by remember { mutableStateOf(false) }

                    LaunchedEffect(item.response) {
                        isResponseReady = item.response != ""
                    }

                    if(isResponseReady){
                        TextMessageResponse(username = "Response", text = item.response, item = item)
                    } else {
                        Text(text = "cek",
                            style = TextStyle(color = Color.White, fontSize = 14.sp)
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            ChatMenuUtility(
                text = text,
                onTextChange = { newText -> text = newText },
                onClickSend = {
                    val newData = GeminiAiResponse(request = text, response = "", timestamp = "")
                    chatViewModel.fetchResponse(data = newData)
                    text = ""
                }
            )
        }
    }
}



@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}


@Preview
@Composable
fun ChatScreenPreview(){
    ChatScreen()
}