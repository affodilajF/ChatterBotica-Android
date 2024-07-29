package com.example.chatterboticaapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chatterboticaapp.ui.navigation.AppNavigationGraph
import com.example.chatterboticaapp.ui.navigation.Routes
import com.example.chatterboticaapp.ui.theme.AppTheme
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Grey02
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.utils.MicrophoneUtils
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        enableEdgeToEdge()

        setContent {

            AppTheme {
                Surface(modifier = Modifier
                    .safeDrawingPadding()
                    .fillMaxSize()
                    .background(color = Black01))
                {
                    val navController = rememberNavController()
//                    MainScreen(navController)
//                    HomeScreen(navController = navController)
//                    AppNavigationGraph(navController)
                    var selectedItem by remember { mutableIntStateOf(0) }
                    val items = listOf(Routes.HOMES_SCREEN, Routes.CHAT_SCREEN, Routes.SPEECH_LISTENING_SCREEN, Routes.DOCS_SCREEN)
                    val icons = listOf(R.drawable.home, R.drawable.chat, R.drawable.micon, R.drawable.doc)

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    val isBottomNavVisible = currentRoute !in listOf(Routes.CHAT_SCREEN+"/{sessionChatId}", Routes.CAMERA_SCREEN, Routes.TEXT_EXTRACTION_SCREEN, Routes.SPEECH_LISTENING_SCREEN)
                    LaunchedEffect(currentRoute) {
                        selectedItem = when (currentRoute) {
                            Routes.HOMES_SCREEN -> 0
                            Routes.CHAT_SCREEN -> 1
                            Routes.SPEECH_LISTENING_SCREEN -> 2
                            Routes.DOCS_SCREEN -> 3
                            else -> 0
                        }
                    }

                    Scaffold(
                        bottomBar = {
                            if (isBottomNavVisible) {
                                BottomAppBar(containerColor = Color.Transparent){
                                    Surface(
                                        shape = RoundedCornerShape(25.dp),
                                        color = AppTheme.colors.Secondary8,
                                        modifier = Modifier.padding(horizontal = 10.dp)
                                    ) {
                                        NavigationBar(containerColor = Color.Transparent) {
                                            items.forEachIndexed { index, item ->
                                                NavigationBarItem(
                                                    icon = { Icon( painter = painterResource(icons[index]),  tint = if (selectedItem == index)  Color.White else  AppTheme.colors.Secondary9, contentDescription = item, modifier = Modifier.size(36.dp)) },
                                                    selected = selectedItem == index,
                                                    onClick = { selectedItem = index
//                                                        navController.navigate(item) {
//                                                            navController.graph.startDestinationRoute?.let { route ->
//                                                                popUpTo(route) { saveState = true }
//                                                            }
//                                                            launchSingleTop = true
//                                                            restoreState = true
//                                                        }
//                                                        when(index){
//                                                            0 -> {navController.navigate(Routes.HOMES_SCREEN)}
//                                                            1 -> {navController.navigate(Routes.CHAT_SCREEN+"/0") }
//                                                            2-> {navController.navigate(Routes.SPEECH_LISTENING_SCREEN)}
//                                                            3-> {navController.navigate(Routes.DOCS_SCREEN)}
//                                                        }
                                                        when (index) {
                                                            0 -> {
                                                                navController.navigate(Routes.HOMES_SCREEN) {
                                                                    // Clear all screens up to the start destination (optional)
                                                                    // This will clear the back stack to only contain the new destination
                                                                    popUpTo(Routes.HOMES_SCREEN) { inclusive = false }
                                                                }
                                                            }
                                                            1 -> {
                                                                navController.navigate(Routes.CHAT_SCREEN + "/0") {
                                                                    // Clear screens up to a certain point in the back stack
                                                                    popUpTo(Routes.CHAT_SCREEN) { inclusive = false }
                                                                }
                                                            }
                                                            2 -> {
                                                                navController.navigate(Routes.SPEECH_LISTENING_SCREEN) {
                                                                    // Clear all screens up to the start destination (optional)
                                                                    popUpTo(Routes.SPEECH_LISTENING_SCREEN) { inclusive = false }
                                                                }
                                                            }
                                                            3 -> {
                                                                navController.navigate(Routes.DOCS_SCREEN) {
                                                                    // Clear screens up to a certain point in the back stack
                                                                    popUpTo(Routes.DOCS_SCREEN) { inclusive = false }
                                                                }
                                                            }
                                                        }

                                                    },
                                                    colors = NavigationBarItemDefaults
                                                        .colors(
                                                            indicatorColor = AppTheme.colors.Secondary8
                                                        )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    ){
                        AppNavigationGraph(navController = navController)
                    }
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