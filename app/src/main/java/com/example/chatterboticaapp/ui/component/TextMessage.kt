package com.example.chatterboticaapp.ui.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextMessageRequest(username: String, text: String){

    Row(modifier = Modifier.padding(bottom = 30.dp)) {
        MiniProfile()
        Spacer(modifier = Modifier.width(7.dp))
        Column{
            Text(text = username, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = text,
                style = TextStyle(color = Color.White, fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun TextMessageResponse(username: String, text: String){

    var textResponse = text;

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

    if (text == "..."){
        textResponse = buildAnnotatedString {
            append("....".substring(0, animatedDots))
        }.toString()
    }

    Row(modifier = Modifier.padding(bottom = 30.dp)) {
        MiniProfile()
        Spacer(modifier = Modifier.width(7.dp))
        Column{
            Text(text = username, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text=textResponse,
                style = TextStyle(color = Color.White, fontSize = 14.sp)
            )
        }
    }
}

