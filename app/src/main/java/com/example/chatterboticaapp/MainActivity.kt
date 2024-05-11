package com.example.chatterboticaapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        enableEdgeToEdge()


        setContent {

            ChatterBoticaAppTheme {
                Surface(modifier = Modifier.safeDrawingPadding().fillMaxSize().background(color = Black01))
                {
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