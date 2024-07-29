package com.example.chatterboticaapp.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.data.model.local.VoiceToTextParserState
import com.example.chatterboticaapp.ui.theme.AppTheme
import com.example.chatterboticaapp.ui.theme.Grey01

@Composable
fun ChatStringText(){
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 11.sp, color = AppTheme.colors.Neutral1)){
                    append("Start new ")
                }
                withStyle(style = SpanStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AppTheme.colors.Neutral1)){
                    append("Chat")
                }
            })
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_forward_24 ),
            contentDescription = "Row Icon",
            tint = AppTheme.colors.Neutral1,
            modifier = Modifier.size(14.dp)
        )
    }
}


@Composable
fun ExtractionStringText(){
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween){
        Column {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AppTheme.colors.Neutral1)){
                        append("Photo ")
                    }
                    withStyle(style = SpanStyle(fontSize = 11.sp, color = AppTheme.colors.Neutral1)){
                        append("to ")
                    }
                    withStyle(style = SpanStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AppTheme.colors.Neutral1)){
                        append("text")
                    }
                }
            )
            Text(
                buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 11.sp, color =  AppTheme.colors.Neutral1)){
                    append("extraction")
                }
            })
        }

        Icon(
            painter = painterResource(R.drawable.baseline_arrow_forward_24 ),
            contentDescription = "Row Icon",
            tint = AppTheme.colors.Neutral1,
            modifier = Modifier.size(14.dp)
        )
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
                    style = TextStyle(fontSize = 16.sp, color = AppTheme.colors.Neutral1),
                    textAlign = TextAlign.Center,
                )
            } else {
                Text(
                    text = state.spokenText,
                    style = TextStyle(fontSize = 16.sp, color = AppTheme.colors.Neutral1),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun TitleBar(){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Column {
            Text(
                text = "Hello, Affodilaj!",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = AppTheme.colors.Neutral1),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "What you want ChatterBotica do for you?",
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, color = AppTheme.colors.Neutral1),
            )
        }
        MediumProfile()
    }
}