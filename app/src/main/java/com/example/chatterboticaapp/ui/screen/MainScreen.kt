package com.example.chatterboticaapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.navigation.AppNavigationGraph
import com.example.chatterboticaapp.ui.navigation.Routes
import com.example.chatterboticaapp.ui.theme.Grey02
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import okhttp3.Route


@Preview
@Composable
fun BottomNavigationBarPreview(){
//    BottomNavigationBar()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController){

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(Routes.HOMES_SCREEN, Routes.CHAT_SCREEN, Routes.SPEECH_LISTENING_SCREEN, Routes.DOCS_SCREEN)
    val icons = listOf(R.drawable.home, R.drawable.chat, R.drawable.micon, R.drawable.doc)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isBottomNavVisible = currentRoute !in listOf(Routes.SPEECH_LISTENING_SCREEN, Routes.CHAT_SCREEN)

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
                        color = GreyPurple01,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        NavigationBar(containerColor = Color.Transparent) {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    icon = { Icon( painter = painterResource(icons[index]),  tint = if (selectedItem == index)  Color.White else  Grey02, contentDescription = item, modifier = Modifier.size(36.dp)) },
                                    selected = selectedItem == index,
                                    onClick = { selectedItem = index
                                        navController.navigate(item) {
                                                navController.graph.startDestinationRoute?.let { route ->
                                                    popUpTo(route) { saveState = true }
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                        }
                                    },
                                    colors = androidx.compose.material3.NavigationBarItemDefaults
                                        .colors(
                                            indicatorColor = GreyPurple01
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





