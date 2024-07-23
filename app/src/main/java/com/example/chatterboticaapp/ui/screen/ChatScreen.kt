package com.example.chatterboticaapp.ui.screen

import android.Manifest.permission.RECORD_AUDIO
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.component.ChatMenuUtility
import com.example.chatterboticaapp.ui.component.GeneratePdfDialog
import com.example.chatterboticaapp.ui.component.SpeechTextDialog
import com.example.chatterboticaapp.ui.component.TextMessageRequest
import com.example.chatterboticaapp.ui.component.TextMessageResponse
import com.example.chatterboticaapp.ui.navigation.Routes
import com.example.chatterboticaapp.ui.theme.Green02
import com.example.chatterboticaapp.ui.viewmodel.ChatViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(navController: NavController, sessionChatId: Long) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var text by remember { mutableStateOf("") }
    var query by remember { mutableStateOf("") }
    var isExit by remember { mutableStateOf(false) }
    var isGeneratePdfClicked by remember { mutableStateOf(false) }
    var pdfFileName by remember { mutableStateOf("") }
    var micIcon by remember { mutableIntStateOf(R.drawable.baseline_mic_off_24) }

    val chatViewModel: ChatViewModel = hiltViewModel()
    val itemListState by chatViewModel.allChat.observeAsState(initial = emptyList())

    val lazyListState = rememberLazyListState()

    val isFetching = chatViewModel.isFetching.value

    val voiceToTextState by chatViewModel.state.collectAsState()
    val pdfFile by chatViewModel.pdfFile.collectAsState()

    val context = LocalContext.current

    // Handle back navigation
    LocalOnBackPressedDispatcherOwner.current?.let {
        BackHandler(backDispatcher = it.onBackPressedDispatcher) {
            isExit = true
            navController.popBackStack()
            navController.navigate(Routes.HOMES_SCREEN)
        }
    }

    // Fetch data when isFetching is true
    LaunchedEffect(isFetching) {
        if(isFetching){
            chatViewModel.fetchResponse(query)
            query = ""
        }
    }

    // Scroll to bottom when new messages arrive
    LaunchedEffect(itemListState) {
        if (itemListState.isNotEmpty()) {
            lazyListState.scrollToItem(itemListState.size-1)
        }
    }

    // Add session chats when exiting the screen
    LaunchedEffect(isExit) {
        if(isExit){
            if(sessionChatId>0){
                chatViewModel.updateSessionChatDb()
            } else {
                chatViewModel.insertSessionChatDb()
            }
            isExit = false
        }
    }

    // For history utility
    LaunchedEffect(Unit) {
        if(sessionChatId > 0){
            chatViewModel.setHistorySessionChat()
        }
    }

    // Handle exit
    DisposableEffect(Unit) {
        chatViewModel.setIdSessionChat(sessionChatId)
        onDispose {
            chatViewModel.setIsFetching(false)
            isExit = true
            chatViewModel.clearSpokenText()
        }
    }

    // If permission granted, do startListening
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        chatViewModel.canRecord.value = isGranted
        if(isGranted){
            chatViewModel.startListening(existedText = "")
        }
    }

    if(chatViewModel.canRecord.value && voiceToTextState.isSpeaking){
        SpeechTextDialog()
        micIcon = R.drawable.baseline_mic_on_24
    } else {
        micIcon = R.drawable.baseline_mic_off_24
    }

    if(isGeneratePdfClicked){
        GeneratePdfDialog(onDismiss = { isGeneratePdfClicked = false}, onGeneratePdf =  {
            chatViewModel.generatePdf(context, pdfFileName)
            isGeneratePdfClicked = false
        }, onTextChange = {newText -> pdfFileName = newText}, pdfFileName = pdfFileName )
    }

    // If spokenText is alr received (as a result of VoiceToTextParser.override onEndOfSpeech()), its time to fetch gpt response
    // by activating setIsFetching
    LaunchedEffect(voiceToTextState.spokenText) {
        if(voiceToTextState.spokenText!=""){
            query = voiceToTextState.spokenText
            chatViewModel.setIsFetching(true)
            chatViewModel.clearSpokenText()
        }
    }

    LaunchedEffect(pdfFile) {
        if(pdfFile != null){
            val result = snackbarHostState
                .showSnackbar(
                    message = "File already generated",
                    actionLabel = "Open",
                    duration = SnackbarDuration.Short
                )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    /* Handle snackbar action performed */
                    chatViewModel.openPdfFile(context)
                    if(sessionChatId>0){
                        chatViewModel.updateSessionChatDb()
                    }else{
                        chatViewModel.insertSessionChatDb()
                    }
                }
                SnackbarResult.Dismissed -> {
                    /* Handle snackbar dismissed */
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { contentPadding ->
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
                    items(itemListState, key = { item -> item.id }) { item ->
                        TextMessageRequest(username = "You", text = item.request)
                        var isResponseReady by remember { mutableStateOf(false) }
                        LaunchedEffect(item.response) {
                            isResponseReady = item.response != ""
                        }
                        if (isResponseReady) {
                            TextMessageResponse(username = "Botica", text = item.response)
                        } else {
                            TextMessageResponse(username = "Botica", "...")
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
                    micIcon = micIcon,
                    isFetchingParam = isFetching,
                    text = text,
                    onTextChange = { newText -> text = newText },
                    onClickSend = {
                        chatViewModel.setIsFetching(true)
                        query = text
                        text = ""
                    },
                    onClickMic = {
                        if (chatViewModel.canRecord.value) {
                            chatViewModel.startListening("")
                        } else {
                            // launch permission request dialog
                            recordAudioLauncher.launch(RECORD_AUDIO)
                        }
                    },
                    onClickPdf = {
                        isGeneratePdfClicked = true
                        Log.d("Cek", contentPadding.toString())
                    }
                )
            }
        }
    }


}

@Preview
@Composable
fun ChatScreenPreview(){

}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}

@Composable
fun BackHandler(
    backDispatcher: OnBackPressedDispatcher,
    onBack: () -> Unit
) {
    DisposableEffect(Unit) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
        backDispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}




