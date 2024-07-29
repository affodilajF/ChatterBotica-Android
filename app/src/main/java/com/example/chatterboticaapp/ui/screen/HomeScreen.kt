package com.example.chatterboticaapp.ui.screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.component.BigBox
import com.example.chatterboticaapp.ui.component.ChatStringText
import com.example.chatterboticaapp.ui.component.ExtractionStringText
import com.example.chatterboticaapp.ui.component.HistoryBox
import com.example.chatterboticaapp.ui.component.MediumBox
import com.example.chatterboticaapp.ui.component.RoundedIconWrapperMini
import com.example.chatterboticaapp.ui.component.TitleBar
import com.example.chatterboticaapp.ui.navigation.Routes
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Grey00
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.theme.Grey02
import com.example.chatterboticaapp.ui.theme.Grey03
import com.example.chatterboticaapp.ui.viewmodel.HomeViewModel
import androidx.compose.animation.core.animateFloat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.onGloballyPositioned
import com.example.chatterboticaapp.ui.theme.AppTheme
import com.example.chatterboticaapp.ui.theme.Grey04
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple03


@Composable
fun HomeScreen(navController: NavController){

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle

    // Use a DisposableEffect to add and remove the observer
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d("HOMESCREEN LifecycleAware", "ON_CREATE HOMESCREEN")
                Lifecycle.Event.ON_START -> Log.d("HOMESCREEN LifecycleAware", "ON_START HOMESCREEN")
                Lifecycle.Event.ON_RESUME -> Log.d("HOMESCREEN LifecycleAware", "ON_RESUME HOMESCREEN")

                Lifecycle.Event.ON_PAUSE -> Log.d("HOMESCREEN LifecycleAware", "ON_PAUSE HOMESCREEN")
                Lifecycle.Event.ON_STOP -> Log.d("HOMESCREEN LifecycleAware", "ON_STOP HOMESCREEN")

                Lifecycle.Event.ON_DESTROY -> Log.d("HOMESCREEN LifecycleAware", "ON_DESTROY HOMESCREEN")
                Lifecycle.Event.ON_ANY -> TODO()
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

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
        .background(color = AppTheme.colors.Background1)
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
                    MediumBox("Chat"){}
                    Spacer(modifier = Modifier.height(13.dp))
                    MediumBox("Extraction"){ navController.navigate(Routes.CAMERA_SCREEN)}
                }
            }
            Box(modifier = Modifier.weight(0.25f),
                contentAlignment = Alignment.Center) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Recent Searchers",
                        color = AppTheme.colors.Tertiary1,
                        style = TextStyle(fontSize = 15.sp))

                    Button(
                        onClick = { homeViewModel.deleteAll() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        )
                    ) {
                        Text(
                            text = "Clear all",
                            color = AppTheme.colors.Tertiary2,
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }
                }
            }

                Box(modifier = Modifier
                    .weight(2f)
                    .padding()){
                    if(isLoadingState){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(bottom = 100.dp)) {
                                CircularProgressIndicator(
                                    color = AppTheme.colors.Tertiary1
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text="Loading", color = AppTheme.colors.Tertiary1)
                            }
                        }
                    }
                    else if (!isHistoryEmptyState){
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = lazyListState,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(itemChatsHistoryState, key = { history -> history.id }) { history ->
                                HistoryBox(history){
                                    val id: Long = history.id
                                    navController.navigate(route = Routes.CHAT_SCREEN + "/$id")
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.height(60.dp)) // Menambahkan ruang kosong di bawah item terakhir
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
                                    color = AppTheme.colors.Tertiary3,
                                    fontSize = 24.sp,
                                ),
                                modifier = Modifier
                                    .padding(bottom = 90.dp) // Optional: Makes the Text fill the width of the parent
                            )
                        }
                    }

            }
//        Box(modifier = Modifier.weight(0.35f))
        }
    }



@Preview
@Composable
fun HomeScreenPreview(){
//    HomeScreen()
//    HistoryBoxUI()
}

