package com.example.chatterboticaapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatterboticaapp.ui.theme.Black01

@Composable
fun ChatScreen(){
    Surface(modifier = Modifier.fillMaxSize(),
        color = Black01
    ) {

        Column {
            Title()
        }

    }
}

@Composable
fun SquareBig(){

}

@Composable
fun SquareMedium(){


}

@Composable
fun Title(){

}





@Preview
@Composable
fun ChatScreenPreview(){
    ChatScreen()
}