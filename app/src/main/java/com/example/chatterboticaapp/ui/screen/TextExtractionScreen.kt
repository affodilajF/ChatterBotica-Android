package com.example.chatterboticaapp.ui.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chatterboticaapp.ui.component.TextButton
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Green02
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.viewmodel.TextExtractionViewModel


@Composable
fun TextExtractionScreen(navController : NavController, textExtractViewModel : TextExtractionViewModel){

    val textExtraction by textExtractViewModel.textExtraction.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        textExtractViewModel.extractText(context)
    }

    Column(modifier = Modifier
        .background(color = Black01)
        .fillMaxSize()
        .padding(horizontal = 18.dp, vertical = 22.dp)) {

        Text(
            text = "Image Text Extraction Result",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .weight(1f)
                .background(Color.White)
                .fillMaxSize(),

        ) {
            if(textExtraction != null){
                Text(text = textExtraction.toString(),
                    modifier = Modifier.padding(20.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    ))
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            color = Green02
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text="Loading", color = Color.Black)
                    }
                }
            }
        }
        Row(modifier = Modifier
            .weight(0.2f)
            .fillMaxWidth()
            .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {

            TextButton(onClick = { navController.popBackStack(navController.graph.startDestinationId, false) }, txt = "Back" , txtColor = Color.White, bgColor = GreyPurple01 )
            TextButton(onClick = { textExtractViewModel.copyToClipboard(context) }, txt = "Copy" , txtColor = Color.Black, bgColor = Green01 )
        }
    }


}

@Preview
@Composable
fun TextExtractionScreenPreview(){
//    val navController = rememberNavController()
//    TextExtractionScreen(navController)
}