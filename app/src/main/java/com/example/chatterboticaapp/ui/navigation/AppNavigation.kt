package com.example.chatterboticaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatterboticaapp.ui.screen.ChatScreen
import com.example.chatterboticaapp.ui.screen.HomeScreen
import com.example.chatterboticaapp.ui.screen.SpeechListeningScreen
import com.example.chatterboticaapp.ui.screen.DocsScreen

@Composable
fun AppNavigationGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = Routes.HOMES_SCREEN){

        composable(Routes.HOMES_SCREEN){
            HomeScreen(navController)
        }

        composable(Routes.SPEECH_LISTENING_SCREEN){
            SpeechListeningScreen()
        }

        composable(Routes.CHAT_SCREEN){
            ChatScreen()
        }

        composable(Routes.DOCS_SCREEN){
            DocsScreen()
        }

    }
}