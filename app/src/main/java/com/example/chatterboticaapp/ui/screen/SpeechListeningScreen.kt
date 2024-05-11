package com.example.chatterboticaapp.ui.screen



import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.data.model.VoiceToTextParserState
import com.example.chatterboticaapp.ui.component.IconTextButton

import com.example.chatterboticaapp.ui.component.TextResultOfSpeech
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple03
import com.example.chatterboticaapp.ui.viewmodel.SpeechListeningViewModel


@Preview
@Composable
fun SpeechListeningPreview(){
}

@Composable
fun SpeechListeningScreen(){

    val viewModel: SpeechListeningViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

//    ini udah mencakup record perms dan speaking state
    val isSpeakingAllowed = remember(viewModel.canRecord.value, state.isSpeaking) {
        viewModel.canRecord.value && state.isSpeaking
    }

    RecordPermsLauncher(viewModel)

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Black01,
    ) {
        Column(modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier.weight(1.5f),
                contentAlignment = Alignment.Center) {
                Title(isSpeakingAllowed)
            }
            Box(modifier = Modifier
                .weight(2f)
                .padding(horizontal = 40.dp)) {
                TextResultOfSpeech(state)
            }
            Box(modifier = Modifier.weight(2f)) {
                SpeechListeningIcon(state, viewModel, isSpeakingAllowed)
            }
            Box(modifier = Modifier.weight(1.2f)) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ){
                    IconTextButton(icon = R.drawable.reset, iconColor = Grey01, txtColor = Grey01, btnColor = GreyPurple03, btnTxt = "Reset") {
                        viewModel.clearSpokenText()
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    IconTextButton(icon = R.drawable.send, iconColor = Color.Black, txtColor = Color.Black, btnColor = Green01, btnTxt = "Send") {

                    }
                }
            }
        }
    }
}

@Composable
fun RecordPermsLauncher(viewModel: SpeechListeningViewModel){
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.canRecord.value = isGranted
        if(isGranted){
            viewModel.startListening(existedText = "")
        }
    }

    LaunchedEffect(key1 = recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }
}


@Composable
fun SpeechListeningIcon(state: VoiceToTextParserState, viewModel: SpeechListeningViewModel, isSpeakingAllowed: Boolean) {
    val infiniteTransitionSpeech = rememberInfiniteTransition(label = "MicIcon")

    val iconResId = if (isSpeakingAllowed) R.drawable.baseline_mic_on_24 else R.drawable.baseline_mic_off_24

    val boxColor = if (isSpeakingAllowed) GreyPurple03 else Black01
    val animatedAlpha by infiniteTransitionSpeech.animateFloat(
//        initialValue = if (state.isSpeaking && canRecord) 0.2f else 1f,
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
                                viewModel.stopListening()
                            }
//                            else if (isSpeakingAllowed){
//                                RecordPermsLauncher(viewModel)
//                            }
                            else {
                                viewModel.startListening(existedText = state.spokenText)
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
fun Title(isSpeakingAllowed: Boolean) {
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

    AnimatedVisibility(
//        visible = state.isSpeaking && canRecord,
        visible = isSpeakingAllowed,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
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
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("listening")
                    }
                    append(buildAnnotatedString {
                        append("....".substring(0, animatedDots))
                    })
                },
                style = TextStyle(fontSize = 17.sp, color = Grey01),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 54.dp)
            )
        }
    }
}

