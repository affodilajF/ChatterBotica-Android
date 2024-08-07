package com.example.chatterboticaapp.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.theme.AppTheme
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Green02
import com.example.chatterboticaapp.ui.theme.GreyPurple01


@Composable
fun ChatMenuUtility(micIcon: Int, isFetchingParam : Boolean, text: String, onTextChange: (String) -> Unit, onClickSend: () -> Unit, onClickMic: ()->Unit, onClickPdf: ()-> Unit){

    val isFetching by rememberUpdatedState(isFetchingParam)
    val color = if (isFetching) Color.Gray else AppTheme.colors.Primary1
    val iconResId = if (isFetching) R.drawable.baseline_rectangle_24 else R.drawable.send

    Surface(modifier = Modifier
        .height(80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .background(color = AppTheme.colors.Secondary4)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
        {
            Box(modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterStart){
                RoundedIconWrapperMedium(drawableIcon = R.drawable.pdf, AppTheme.colors.Secondary8, Color.White){
                    onClickPdf()
                }
            }
            Box(modifier = Modifier.weight(1.8f),
                contentAlignment = Alignment.Center){
                TextInputField(
                    txtHint = "",
                    txtColor = Color.White,
                    bgColor = AppTheme.colors.Secondary8,
                    text = text,
                    onTextChange = onTextChange
                )
            }
            Box(modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterEnd){
                RoundedIconWrapperMedium(drawableIcon = micIcon, AppTheme.colors.Secondary8, Color.White){
                    onClickMic()
                }

            }
            Box(modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterEnd){

                RoundedIconWrapperMedium(drawableIcon = iconResId, color, Color.Black){
                    onClickSend()
                }

            }
        }
    }
}

