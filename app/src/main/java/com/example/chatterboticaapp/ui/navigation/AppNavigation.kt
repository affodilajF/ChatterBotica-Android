package com.example.chatterboticaapp.ui.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chatterboticaapp.ui.screen.CameraScreen
import com.example.chatterboticaapp.ui.screen.CameraScreen
import com.example.chatterboticaapp.ui.screen.ChatScreen
import com.example.chatterboticaapp.ui.screen.HomeScreen
import com.example.chatterboticaapp.ui.screen.SpeechListeningScreen
import com.example.chatterboticaapp.ui.screen.DocsScreen
import com.example.chatterboticaapp.ui.screen.TextExtractionScreen
import com.example.chatterboticaapp.ui.viewmodel.TextExtractionViewModel

@Composable
fun AppNavigationGraph(navController: NavHostController){

    NavHost(enterTransition = {
                fadeIn(
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh
                ),
                initialAlpha = 0.001f // Start with 20% opacity
                )},
            exitTransition = {
                fadeOut(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMediumLow
                ), targetAlpha = 0f ) } ,
        navController = navController, startDestination = Routes.HOMES_SCREEN){
            composable(Routes.HOMES_SCREEN){
                HomeScreen(navController)
            }
            composable(Routes.SPEECH_LISTENING_SCREEN){
                SpeechListeningScreen(navController)
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
                DocsScreen(navController)
            }

            navigation(
                startDestination = Routes.CAMERA_SCREEN,
                route = Routes.TEXT_EXTRACTION_FEATURE
            ){
                composable(Routes.CAMERA_SCREEN){ entry ->
                    val viewModel = entry.textExtractionViewModel<TextExtractionViewModel>(navController)
                    CameraScreen(navController = navController, textExtractViewModel = viewModel)

                }
                composable(Routes.TEXT_EXTRACTION_SCREEN){ entry ->
                    val viewModel = entry.textExtractionViewModel<TextExtractionViewModel>(navController)
                    TextExtractionScreen(navController = navController, textExtractViewModel = viewModel)
                }
            }
        }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.textExtractionViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}