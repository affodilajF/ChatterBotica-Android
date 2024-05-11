package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextMessage(username: String, text: String){
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

