package com.example.chatterboticaapp.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.component.BigBox
import com.example.chatterboticaapp.ui.component.HistoryBox
import com.example.chatterboticaapp.ui.component.MediumBox
import com.example.chatterboticaapp.ui.component.TitleBar
import com.example.chatterboticaapp.ui.navigation.Routes
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.theme.Grey02
import com.example.chatterboticaapp.ui.theme.Grey03
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.viewmodel.ChatViewModel
import com.example.chatterboticaapp.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(navController: NavController){

    val homeViewModel: HomeViewModel = hiltViewModel()
    val lazyListState = rememberLazyListState()

    val isLoadingState by homeViewModel.isLoading.collectAsState()
    val isHistoryEmptyState by homeViewModel.isHistoryEmpty.collectAsState()

    val itemChatsHistoryState by homeViewModel.historyChat.collectAsState()
    LaunchedEffect(Unit) {
        homeViewModel.getAllHistory()
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Black01)
        .padding(horizontal = 18.dp, vertical = 22.dp)
            ,
        ) {
            Box(modifier = Modifier.weight(0.40f),
                contentAlignment = Alignment.TopStart) {
                TitleBar()
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BigBox(onClick = { navController.navigate(Routes.SPEECH_LISTENING_SCREEN) })
                Column {
                    MediumBox("Chat")
                    Spacer(modifier = Modifier.height(13.dp))
                    MediumBox("Extraction")
                }
            }
            // Temporary button
//            Box(modifier = Modifier.weight(0.5f)){
//                Button(onClick = { homeViewModel.deleteAll() }) {
//                }
//            }
            Box(modifier = Modifier.weight(0.25f),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Recent Searchers",
                    color = Grey01,
                    style = TextStyle(fontSize = 14.sp))
            }

                Box(modifier = Modifier.weight(2f)){
                    if(isLoadingState){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text(text="Loading ... ", color = Color.White)
                            CircularProgressIndicator()
                        }
                    }
                    else if (!isHistoryEmptyState){
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = lazyListState
                        ) {
                            items(itemChatsHistoryState, key = { history -> history.id }) { history ->
                                HistoryBox(history){
                                    val id: Long = history.id
                                    navController.navigate(route = Routes.CHAT_SCREEN + "/$id")
                                }
                            }
                        }
                    }
                    else if(isHistoryEmptyState){
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "No history found",
                                style = TextStyle(
                                    color = Grey03,
                                    fontSize = 24.sp,
                                ),
                                modifier = Modifier
                                    .padding(bottom = 90.dp) // Optional: Makes the Text fill the width of the parent
                            )
                        }
                    }

            }
        }
    }



@Preview
@Composable
fun HomeScreenPreview(){
//    HomeScreen()
}