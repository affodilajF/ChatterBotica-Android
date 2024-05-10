package com.example.chatterboticaapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.chatterboticaapp.ui.screen.MainScreen
import com.example.chatterboticaapp.utils.VoiceToTextParser
import com.example.chatterboticaapp.ui.screen.SpeechListeningScreen
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.ChatterBoticaAppTheme
import com.example.chatterboticaapp.utils.MicrophoneUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val voiceToTextParser by lazy{
        VoiceToTextParser(application)
    }

    private val MicrophoneUtils by lazy{
        MicrophoneUtils(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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


                    val navController = rememberNavController()
                    MainScreen(navController)

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