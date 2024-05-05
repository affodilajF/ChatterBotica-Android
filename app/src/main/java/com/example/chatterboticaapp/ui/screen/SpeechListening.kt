package com.example.chatterboticaapp.ui.screen



import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.VoiceToTextParser
import com.example.chatterboticaapp.ui.VoiceToTextParserState
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.theme.Grey02
import com.example.chatterboticaapp.ui.theme.Grey03
import com.example.chatterboticaapp.ui.theme.Grey04
import com.example.chatterboticaapp.ui.theme.Grey05
import com.example.chatterboticaapp.ui.theme.PrimaryGreen



@Preview
@Composable
fun SpeechListeningPreview(){
//    SpeechListening()
}

@Composable
fun SpeechListening(voiceToTextParser : VoiceToTextParser){

    var canRecord by remember { mutableStateOf(true) }

    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        canRecord = isGranted
        voiceToTextParser.startListening(existedText = "")
    }

    LaunchedEffect(key1 = recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    val state by voiceToTextParser.state.collectAsState()


    Surface(modifier = Modifier.fillMaxSize(),
        color = Black01,
        ) {
        Column(modifier = Modifier.fillMaxSize(),
            ) {
            Box(modifier = Modifier.weight(1.5f),
                contentAlignment = Alignment.Center) {
                Title(state)
            }
            Box(modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 40.dp)) {
                TextResultOfSpeech(state)
            }
            Box(modifier = Modifier.weight(2f)) {
               SpeechListeningIcon(state, voiceToTextParser)
            }
            Box(modifier = Modifier.weight(1.2f)) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ){
                    ResetButton(voiceToTextParser)
                    SendButton(state, voiceToTextParser)
                }
            }
        }
    }
}



@Composable
fun SpeechListeningIcon(state: VoiceToTextParserState, voiceToTextParser: VoiceToTextParser) {
    val infiniteTransitionSpeech = rememberInfiniteTransition(label = "MicIcon")

    val iconResId = if (state.isSpeaking) R.drawable.baseline_mic_on_24 else R.drawable.baseline_mic_off_24

    // Tentukan warna default dan animasi alpha berdasarkan state.isSpeaking
    val boxColor = if (state.isSpeaking) Grey03 else Black01
    val animatedAlpha by infiniteTransitionSpeech.animateFloat(
        initialValue = if (state.isSpeaking) 0.2f else 1f,
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
                .size(170.dp)
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
                                voiceToTextParser.stopListening()
                            } else {
                                voiceToTextParser.startListening(existedText = state.spokenText)
                            }
                        } 
                    )
                    .size(120.dp)
                    .background(color = Grey05, shape = RoundedCornerShape(percent = 50)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = if (state.isSpeaking) "Keyboard Voice Icon" else "Search Icon",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}



@Composable
fun TextResultOfSpeech(state: VoiceToTextParserState) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = state.isSpeaking, // Gunakan variabel isSpeaking di sini
            label = "SpeechAnimation"
        ) { speaking ->
            if (speaking) {
                Text(
                    text = state.spokenText,
                    style = TextStyle(fontSize = 16.sp, color = Color.White),
                    textAlign = TextAlign.Center,
                )
            } else {
                Text(
                    text = state.spokenText,
                    style = TextStyle(fontSize = 16.sp, color = Color.White),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun Title(state: VoiceToTextParserState) {
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
        visible = state.isSpeaking,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .height(50.dp)
                .background(color = Grey03, shape = RoundedCornerShape(25.dp)),
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
                style = TextStyle(fontSize = 18.sp, color = Grey04),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 54.dp)
            )
        }
    }
}



@Composable
fun ResetButton(voiceToTextParser: VoiceToTextParser) {
    Surface(
        color = Grey02,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .size(width = 130.dp, height = 40.dp)
            .clickable(
                onClick = { voiceToTextParser.clearSpokenText() }
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = "Reset",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Grey01),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reset Icon",
                tint = Grey01
            )
        }
    }
}


@Composable
fun SendButton(state : VoiceToTextParserState, voiceToTextParser : VoiceToTextParser ){
    Surface(
        color = PrimaryGreen,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .size(width = 130.dp, height = 40.dp)
            .clickable(
                onClick = {
//                    if (state.isSpeaking) {
//                            voiceToTextParser.stopListening()
//                        } else {
//                            voiceToTextParser.startListening()
//                        }
                    Log.d("MyApp", state.spokenText)
                }
            ),

    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(

                text = "Send",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.End
            )
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Search Icon",

                tint = Color.Black
            )
        }
    }
}