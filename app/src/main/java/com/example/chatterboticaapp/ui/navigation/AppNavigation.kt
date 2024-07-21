package com.example.chatterboticaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            route = "${Routes.CHAT_SCREEN}/{sessionChatId}",
            arguments = listOf(navArgument("sessionChatId") { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val sessionChatId = arguments.getLong("sessionChatId")
            ChatScreen(navController = navController, sessionChatId = sessionChatId)
        }
        composable(Routes.DOCS_SCREEN){
            DocsScreen()
        }

    }
}