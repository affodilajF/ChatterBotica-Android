package com.example.chatterboticaapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.ui.theme.Black01


@Composable
fun DocsScreen(){
    Surface(modifier = Modifier.fillMaxSize(),
        color = Black01
    ) {
        Text(
            text = "Docs Screen",
            style = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

    }
}
