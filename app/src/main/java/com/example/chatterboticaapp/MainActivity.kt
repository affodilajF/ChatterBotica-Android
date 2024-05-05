package com.example.chatterboticaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatterboticaapp.ui.VoiceToTextParser
import com.example.chatterboticaapp.ui.navigation.AppNavigationGraph
import com.example.chatterboticaapp.ui.navigation.AppNavigationGraph
import com.example.chatterboticaapp.ui.screen.SpeechListening
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.ChatterBoticaAppTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chatterboticaapp.ui.screen.HomeScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val voiceToTextParser by lazy{
        VoiceToTextParser(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//
//            var canRecord by remember { mutableStateOf(true) }
////
//            val recordAudioLauncher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.RequestPermission()
//            ) { isGranted ->
//                canRecord = isGranted
////                voiceToTextParser.startListening()
//            }
//
//            LaunchedEffect(key1 = recordAudioLauncher) {
//                recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
//            }
//
//            val state by voiceToTextParser.state.collectAsState()

//            Scaffold (
//                floatingActionButton = {
//                    FloatingActionButton(
//                                onClick = {
//                        if (state.isSpeaking) {
//                            voiceToTextParser.stopListening()
//                        } else {
//                            voiceToTextParser.startListening()
//                        }
//
//
//                    }) {
//
//                        AnimatedContent(targetState = state.isSpeaking, label = "Ya") { isSpeaking ->
//                            if(isSpeaking){
//                                Icon(
//                                    imageVector = Icons.Default.StopCircle,
//                                    contentDescription = "Search Icon",
//                                    tint = Color.Black
//                                )
//                            }
//
//                        }
//
//                    }
//                }
//            ){ padding ->
//                Column( modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(20.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally) {
//
//                    AnimatedContent(targetState = state.isSpeaking, label = "Ya2") { isSpeaking ->
//                        if(isSpeaking){
//                            Text(text = "Speaking...")
//                        } else {
//                            Text(text=state.spokenText.ifEmpty {
//                                "isSpeaking is false"
//                            })
//                        }
//                    }
//                }
//            }


            ChatterBoticaAppTheme {
//                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Black01
                ) {
//                    AppEntryPoint()

                    SpeechListening(voiceToTextParser)
//                    HomeScreen()

                }
            }






        }
    }
}












//            ChatterBoticaAppTheme {
////                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier
//                                .fillMaxSize(),
//                    color = Black01
//                ) {
////                    AppEntryPoint()
//
////                    SpeechListening()
//                }
//            }

//@Composable
//fun AppEntryPoint(){
//    AppNavigationGraph()
//}
//
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ChatterBoticaAppTheme {
//        Greeting("Android")
//    }
//}