package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.theme.Green02

@Composable
fun SpeechTextDialog() {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.4f),
        containerColor = Green02,
        icon = {
            Image(
                painter = painterResource(id = R.drawable.robot),
                contentDescription = "profile pic",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 20.dp)
                    .clip(CircleShape)
            )
        },
        onDismissRequest = {},
        title = {
            Text("Botica Is Listening...",
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        },
        dismissButton = {},
        confirmButton = {}
    )
}