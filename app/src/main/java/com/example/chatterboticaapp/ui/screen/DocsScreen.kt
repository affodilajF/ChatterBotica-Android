package com.example.chatterboticaapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.ui.theme.Black01


@Composable
fun DocsScreen(){
//    Surface(modifier = Modifier.fillMaxSize(),
//        color = Black01
//    ) {
//        Text(
//            text = "Docs Screen",
//            style = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold),
//            textAlign = TextAlign.Center
//        )
//
//    }
    // Define a state variable for the count
    val count = remember { mutableIntStateOf(0) }


    // Use SideEffect to log the current value of count
    SideEffect {
        Log.d("cek","Count is ${count.intValue}")
    }

//    LaunchedEffect(count.intValue) {
//        Log.d("cek","LaunchedEffect, Count is ${count.intValue}")
//    }


    Column {
        Button(onClick = { count.intValue++ }) {
            Text("Increase Count ${count.intValue}")
//            Log.d("cek","Count iss ${count.intValue}")
        }

        Text("Increase Count ${count.intValue}")
    }
}
