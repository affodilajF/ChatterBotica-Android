package com.example.chatterboticaapp.ui.screen

import android.app.Activity
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.component.HistoryBox
import com.example.chatterboticaapp.ui.component.RoundedIconWrapperMedium
import com.example.chatterboticaapp.ui.component.RoundedIconWrapperMini
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Grey03
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple03
import com.example.chatterboticaapp.ui.theme.GreyPurple04
import com.example.chatterboticaapp.utils.KeyboardListener
import com.example.chatterboticaapp.utils.rememberImeState

@Composable
fun ChatScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
    ){
        Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center) {

        }
        Box(
            modifier = Modifier
                .weight(0.12f)
                .padding(top = 4.dp)
        ) {
            Message()
        }
    }
}



@Composable
fun Message(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .background(color = Black01)
                .padding(horizontal = 16.dp))
        {
            Box(modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterStart){
                RoundedIconWrapperMedium(drawableIcon = R.drawable.pdf, GreyPurple01, Color.White)
            }
            Box(modifier = Modifier.weight(1.8f),
                contentAlignment = Alignment.Center){
                TextFieldMessage()
            }
            Box(modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterEnd){
                RoundedIconWrapperMedium(drawableIcon = R.drawable.micoff, GreyPurple01, Color.White)

            }
           Box(modifier = Modifier.weight(0.5f),
               contentAlignment = Alignment.CenterEnd){
                RoundedIconWrapperMedium(drawableIcon = R.drawable.send, Green01, Color.Black)

            }
        }
    }
}

@Composable
fun TextFieldMessage() {
    var text by remember { mutableStateOf("Hello") }

    TextField(
        maxLines = 2,
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(color = Color.White),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = GreyPurple03,
            focusedContainerColor = GreyPurple03
        ),
    )
}


@Preview
@Composable
fun ChatScreenPreview(){
    ChatScreen()
}