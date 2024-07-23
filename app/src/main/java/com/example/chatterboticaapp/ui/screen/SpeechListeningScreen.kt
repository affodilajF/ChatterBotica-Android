package com.example.chatterboticaapp.ui.screen
import android.Manifest
import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.data.model.local.VoiceToTextParserState
import com.example.chatterboticaapp.ui.component.IconTextButton
import com.example.chatterboticaapp.ui.component.TextResultOfSpeech
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple03
import com.example.chatterboticaapp.ui.viewmodel.GenerativeAIViewModel
import com.example.chatterboticaapp.ui.viewmodel.STTViewModel
import com.example.chatterboticaapp.ui.viewmodel.TTSViewModel

@Preview
@Composable
fun SpeechListeningPreview(){
}

@Composable
fun SpeechListeningScreen(navController: NavController){

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    if (backDispatcher != null) {
        BackHandler(onBack = {
            navController.popBackStack(navController.graph.startDestinationId, false)
        }, backDispatcher = backDispatcher)
    }

    val ttsViewModel : TTSViewModel = hiltViewModel()
    val sttViewModel: STTViewModel = hiltViewModel()
    val generativeAIViewModel: GenerativeAIViewModel = hiltViewModel()

    val voiceToTextState by sttViewModel.state.collectAsState()
    val mediaPlayingState by ttsViewModel.isMediaPlayingState.collectAsState()
    val playHTFetchingState by ttsViewModel.isPlayHTFetchingState.collectAsState()

//    ini udah mencakup record perms dan speaking state
    val isSpeakingAllowed = remember(sttViewModel.canRecord.value, voiceToTextState.isSpeaking) {
        sttViewModel.canRecord.value && voiceToTextState.isSpeaking
    }

    RecordPermsLauncher(sttViewModel)

    DisposableEffect(Unit) {
        onDispose {
            sttViewModel.clearSpokenText()
            sttViewModel.stopListening()
        }
    }

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Black01,
    ) {
        Column(modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier.weight(1.5f),
                contentAlignment = Alignment.Center) {
                if(isSpeakingAllowed){
                    Title("listening ",  80)
                } else if(mediaPlayingState){
                    Title("speaking ",  80)
                } else if (playHTFetchingState){
//                   Title(playHTFetchingState, "FETCHING")
                }
            }
            Box(modifier = Modifier
                .weight(2f)
                .padding(horizontal = 40.dp)

            ) {
//                RobotIconTalking()

                if(!mediaPlayingState){
                    TextResultOfSpeech(voiceToTextState)
                }
                if(playHTFetchingState){
                    Title("fetching ", 40)
                }
                if(mediaPlayingState && !playHTFetchingState){
                    RobotIconTalking()
                }

            }
            Box(modifier = Modifier.weight(2f)) {

                if(!mediaPlayingState && !playHTFetchingState){
                    SpeechListeningIcon(voiceToTextState, sttViewModel, isSpeakingAllowed)
                }


            }
            Box(modifier = Modifier.weight(1.2f)) {
                if(!mediaPlayingState && !playHTFetchingState){
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ){
                        IconTextButton(icon = R.drawable.reset, iconColor = Grey01, txtColor = Grey01, btnColor = GreyPurple03, btnTxt = "Reset") {
                            sttViewModel.clearSpokenText()
                        }
                        Spacer(modifier = Modifier.width(24.dp))
                        IconTextButton(icon = R.drawable.send, iconColor = Color.Black, txtColor = Color.Black, btnColor = Green01, btnTxt = "Send") {

                            generativeAIViewModel.fetchResponse(voiceToTextState.spokenText) { result ->
                                Log.d("MyComposable", "Hasil dari generative model yeah: ${result.response}")
                                ttsViewModel.generateSpeech(result.response)
                                sttViewModel.clearSpokenText()

                            }
                        }
                    }
                }
            }
        }
    }
}






@Composable
fun RecordPermsLauncher(sttViewModel: STTViewModel){
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        sttViewModel.canRecord.value = isGranted
        if(isGranted){
            sttViewModel.startListening(existedText = "")
        }
    }

    LaunchedEffect(key1 = recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }
}

@Composable
fun RobotIconTalking(){

    val infiniteTransitionDots = rememberInfiniteTransition(label = "Dots.")
    val animatedDots by infiniteTransitionDots.animateValue(
        initialValue = 0,
        targetValue = 5,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Restart
        ), label = "Dots."
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.robot),
                contentDescription = "ya",
                tint = Green01,
                modifier = Modifier.size(220.dp)
            )
        }

        Box(modifier = Modifier.weight(0.3f)
        ){
            Text(
                text = buildAnnotatedString {
                    append("....".substring(0, animatedDots)) },
                style = TextStyle(fontSize = 37.sp, color = Green01),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun SpeechListeningIcon(state: VoiceToTextParserState, sttViewModel: STTViewModel, isSpeakingAllowed: Boolean) {
    val infiniteTransitionSpeech = rememberInfiniteTransition(label = "MicIcon")

    val iconResId = if (isSpeakingAllowed) R.drawable.baseline_mic_on_24 else R.drawable.baseline_mic_off_24

    val boxColor = if (isSpeakingAllowed) GreyPurple03 else Black01
    val animatedAlpha by infiniteTransitionSpeech.animateFloat(
        initialValue = if (isSpeakingAllowed) 0.2f else 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ), label = "mic"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(
                    color = boxColor.copy(alpha = animatedAlpha), // Gunakan alpha yang diatur oleh animasi
                    shape = RoundedCornerShape(percent = 50)
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            if (state.isSpeaking) {
                                sttViewModel.stopListening()
                            } else {
                                sttViewModel.startListening(existedText = state.spokenText)
                            }
                        }
                    )
                    .size(110.dp)
                    .background(color = GreyPurple01, shape = RoundedCornerShape(percent = 50)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = if (isSpeakingAllowed) "Mic On Icon" else "Mic Off Icon",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun Title(text: String, padding: Int) {
    val infiniteTransitionDots = rememberInfiniteTransition(label = "Dots")

    val animatedDots by infiniteTransitionDots.animateValue(
        initialValue = 0,
        targetValue = 5,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Restart
        ), label = "Dots"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp)
            .height(50.dp)
            .background(color = GreyPurple01, shape = RoundedCornerShape(25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                append("Botica is ")
                withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)) {
                    append(text)
                }
                append(buildAnnotatedString {
                    append("....".substring(0, animatedDots))
                })
            },
            style = TextStyle(fontSize = 17.sp, color = Grey01),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dp)
        )
    }
}
