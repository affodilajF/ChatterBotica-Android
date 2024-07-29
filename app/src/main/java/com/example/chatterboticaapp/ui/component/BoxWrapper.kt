package com.example.chatterboticaapp.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.data.model.local.SessionChats
import com.example.chatterboticaapp.ui.theme.AppTheme
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Grey00
import com.example.chatterboticaapp.ui.theme.Grey04
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple03
import com.example.chatterboticaapp.utils.TimestampUtils

@Composable
fun MediumBox(text: String, onClick: () -> Unit){
    Surface(modifier = Modifier
        .height(105.dp)
        .width(170.dp),
        color = AppTheme.colors.Secondary5,
        shape = RoundedCornerShape(10.dp)
    ) {

        Box(modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                onClick()
            })
            .background(color = AppTheme.colors.Secondary3 , shape = RoundedCornerShape(10.dp)))
        {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 6.dp),

                ) {


               if(text=="Chat"){
                   RoundedIconWrapperMini(drawableIcon = R.drawable.chat, AppTheme.colors.Secondary1, AppTheme.colors.Secondary6)
                   Spacer(modifier = Modifier.height(6.dp))
                   ChatStringText()
               } else if (text=="Extraction"){
                   RoundedIconWrapperMini(drawableIcon = R.drawable.extraction, AppTheme.colors.Secondary1, AppTheme.colors.Secondary6)
                   Spacer(modifier = Modifier.height(6.dp))
                   ExtractionStringText()
               }

            }
        }

    }
}

@Composable
fun BigBox(onClick: () -> Unit){
    Surface(
        modifier = Modifier
            .height(222.dp)
            .width(170.dp),
        color = AppTheme.colors.Secondary5,
        shape = RoundedCornerShape(10.dp)
    ){
        Box(
            modifier = Modifier
                .padding(10.dp) // Adjust dimensions and padding as needed
                .background(color = AppTheme.colors.Secondary3, shape = RoundedCornerShape(percent = 5)),
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp, color = AppTheme.colors.Neutral1 )) {
                            append("Talking ")
                        }
                        withStyle(style = SpanStyle(fontSize = 11.sp, color = AppTheme.colors.Neutral1 )) {
                            append("with\n")
                            append("ChatterBotica!")
                        }
                    },
                    Modifier.weight(1f)
                )
                TextButton(onClick, txt = "Let's Talk!", txtColor = AppTheme.colors.Neutral2, bgColor = AppTheme.colors.Primary1)
            }
        }
    }
}

@Composable
fun HistoryBox(history : SessionChats, onClick: () -> Unit){

    val fadeInTransition = remember {
        fadeIn(
            animationSpec = spring(
                stiffness = Spring.StiffnessHigh
            ),
            initialAlpha = 0.5f // Fully transparent
        )
    }

    val slideInTransition = remember {
        slideIn(
            initialOffset = { fullSize ->
                IntOffset(fullSize.width, 0) // Slide in from the right
            },
            animationSpec = tween(durationMillis = 0) // Customize duration
        )
    }
    AnimatedVisibility(visible = true) {


    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }
        .background(color = AppTheme.colors.Secondary5, shape = RoundedCornerShape(percent = 40)),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Column {
                Text(
                    text = history.title,
                    style = TextStyle(fontSize = 14.sp, color = AppTheme.colors.Neutral1),
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = TimestampUtils.getDuration(history.timestamp),
                    style = TextStyle(fontSize = 8.sp, color = AppTheme.colors.Neutral1),
                )

            }
            RoundedIconWrapperMini(drawableIcon = R.drawable.baseline_arrow_forward_24, colorWrapper = AppTheme.colors.Secondary7, AppTheme.colors.Secondary6)
        }
    }
    }
}