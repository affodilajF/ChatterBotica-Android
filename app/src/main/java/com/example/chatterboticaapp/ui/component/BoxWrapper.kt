package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.theme.Grey00
import com.example.chatterboticaapp.ui.theme.Grey04
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple03

@Composable
fun MediumBox(text: String){
    Surface(modifier = Modifier
        .height(105.dp)
        .width(170.dp),
        color = Grey04,
        shape = RoundedCornerShape(10.dp)
    ) {

        Box(modifier = Modifier
            .padding(8.dp)
            .background(color = GreyPurple03, shape = RoundedCornerShape(10.dp)))
        {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 6.dp),

                ) {


               if(text=="Chat"){
                   RoundedIconWrapperMini(drawableIcon = R.drawable.chat, GreyPurple01)
                   Spacer(modifier = Modifier.height(6.dp))
                   ChatStringText()
               } else if (text=="Extraction"){
                   RoundedIconWrapperMini(drawableIcon = R.drawable.extraction, GreyPurple01)
                   Spacer(modifier = Modifier.height(6.dp))
                   ExtractionStringText()
               }

            }
        }

    }
}

@Composable
fun BigBox(){
    Surface(
        modifier = Modifier
            .height(222.dp)
            .width(170.dp),
        color = Grey04,
        shape = RoundedCornerShape(10.dp)
    ){
        Box(
            modifier = Modifier
                .padding(10.dp) // Adjust dimensions and padding as needed
                .background(color = GreyPurple03, shape = RoundedCornerShape(percent = 5)),
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.White )) {
                            append("Talking ")
                        }
                        withStyle(style = SpanStyle(fontSize = 11.sp, color = Color.White )) {
                            append("with\n")
                            append("ChatterBotica!")
                        }
                    },
                    Modifier.weight(1f)
                )
                TalkingButton()
            }
        }
    }
}

@Composable
fun HistoryBox(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp)
        .background(color = Grey04, shape = RoundedCornerShape(percent = 40)),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Column {
                Text(
                    text = "What is mangga?",
                    style = TextStyle(fontSize = 14.sp, color = Color.White),
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Today",
                    style = TextStyle(fontSize = 8.sp, color = Color.White),
                )

            }
            RoundedIconWrapperMini(drawableIcon = R.drawable.baseline_arrow_forward_24, colorWrapper = Grey00)
        }
    }
}