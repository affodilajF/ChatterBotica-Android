package com.example.chatterboticaapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.component.BigBox
import com.example.chatterboticaapp.ui.component.HistoryBox
import com.example.chatterboticaapp.ui.component.MediumBox
import com.example.chatterboticaapp.ui.navigation.Routes
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.viewmodel.HomeViewModel
import com.example.chatterboticaapp.ui.viewmodel.SpeechListeningViewModel

@Composable
fun HomeScreen(navController: NavController){
        val viewModel: HomeViewModel = hiltViewModel()

//        val navController = rememberNavController()
//        viewModel.setNavController(navController)



        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Black01)
            .padding(horizontal = 22.dp, vertical = 22.dp)
            ,
        ) {
            Box(modifier = Modifier.weight(0.35f)) {
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
            Box(modifier = Modifier.weight(0.25f),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Recent Searchers",
                    color = Grey01,
                    style = TextStyle(fontSize = 14.sp))
            }
            Column(modifier = Modifier.weight(2f)) {
                HistoryBox()
                HistoryBox()
                HistoryBox()
            }
        }
    }

@Composable
fun TitleBar(){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Column {
            Text(
                text = "Hello, Affodilaj!",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "What you want ChatterBotica do for you?",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.White),
            )
        }
        Profile()
    }
}




@Composable
fun Profile(){
    Image(
        painter = painterResource(id = R.drawable.temprofilepic),
        contentDescription = "profile pic",
        modifier = Modifier
            .size(56.dp)
            .padding(4.dp)
            .clip(CircleShape)
    )
}



@Preview
@Composable
fun HomeScreenPreview(){
//    HomeScreen()
}