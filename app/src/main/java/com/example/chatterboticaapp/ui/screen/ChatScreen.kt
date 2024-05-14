package com.example.chatterboticaapp.ui.screen

import android.app.Activity
import android.graphics.Rect
import android.view.ViewTreeObserver
import android.view.WindowInsetsController
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.component.ChatMenuUtility
import com.example.chatterboticaapp.ui.component.HistoryBox
import com.example.chatterboticaapp.ui.component.RoundedIconWrapperMedium
import com.example.chatterboticaapp.ui.component.RoundedIconWrapperMini
import com.example.chatterboticaapp.ui.component.TextMessage
import com.example.chatterboticaapp.ui.theme.Black00
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Grey03
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple03
import com.example.chatterboticaapp.ui.theme.GreyPurple04
import com.example.chatterboticaapp.ui.viewmodel.GenerativeAIViewModel
import com.example.chatterboticaapp.utils.KeyboardListener
import com.example.chatterboticaapp.utils.rememberImeState
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun ChatScreen(){

    val viewModel: GenerativeAIViewModel = hiltViewModel()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Black01)
        .padding(top = 18.dp)
    ){
        Box(modifier = Modifier
            .weight(1f)
            .padding(horizontal = 20.dp),
        )
        {
            LazyColumn {
                items(15) {
                    TextMessage("You", "Apa itu mangga? Apakah enak? Apakah segar? apakah ya heum ya oke sih? okelah tapi? oke ya oke oke")
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(0.2f)
                .padding(top = 4.dp)
        ) {
            ChatMenuUtility(viewModel)
        }
    }
}











@Preview
@Composable
fun ChatScreenPreview(){
    ChatScreen()
}