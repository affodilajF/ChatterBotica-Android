package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.data.model.GeminiAiResponse


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
fun TextMessageResponse(username: String, text: String, item: GeminiAiResponse){

    var textContent by remember {mutableStateOf(item.response)}

    Row(modifier = Modifier.padding(bottom = 30.dp)) {
        MiniProfile()
        Spacer(modifier = Modifier.width(7.dp))
        Column{
            Text(text = username, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = textContent,
                style = TextStyle(color = Color.White, fontSize = 14.sp)
            )
        }
    }
}

